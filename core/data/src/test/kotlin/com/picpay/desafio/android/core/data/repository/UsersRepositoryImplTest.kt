package com.picpay.desafio.android.core.data.repository

import com.picpay.desafio.android.core.model.ApiResponse
import com.picpay.desafio.android.core.data.di.test.testingDataModule
import com.picpay.desafio.android.core.data.image.fake.FakeAppImageProcessor
import com.picpay.desafio.android.core.model.UserModel
import com.picpay.desafio.android.core.data.mappers.toDomainModel
import com.picpay.desafio.android.core.data.repository.fake.FakeUserLocalDataSource
import com.picpay.desafio.android.core.data.repository.fake.FakeUserRemoteDataSource
import com.picpay.desafio.android.core.data.repository.fake.FakeUsersRepository
import com.picpay.desafio.android.core.data.repository.fake.lists.fakeUserModelList
import com.picpay.desafio.android.core.data.repository.fake.lists.fakeUserResponseList
import com.picpay.desafio.android.core.data.sync.Synchronizer
import com.picpay.desafio.android.core.data.sync.usersSync
import com.picpay.desafio.android.core.data.util.OrderDirection
import com.picpay.desafio.android.core.data.util.SortBy
import com.picpay.desafio.android.core.network.model.UserResponse
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject

class UsersRepositoryImplTest: KoinTest {

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(testingDataModule)
    }

    private val synchronizer: Synchronizer by inject()

    private val testDispatcher: TestDispatcher by inject()

    private val imageProcessor: FakeAppImageProcessor by inject()

    private val usersRepository: FakeUsersRepository by inject()
    private val remoteDataSource: FakeUserRemoteDataSource by inject()
    private val localDataSource: FakeUserLocalDataSource by inject()


    private val populatedListOfUsers = fakeUserModelList.toMutableList()

    @Before
    fun setUp() {
        localDataSource.users = mutableListOf()
    }

    @After
    fun cleanUp() {
        localDataSource.users = mutableListOf()
    }

    private suspend fun fetchUsers() = withContext(testDispatcher) {
        return@withContext kotlin.run {
            when (val apiResponse = remoteDataSource.getUsers()) {
                is ApiResponse.Error -> null
                is ApiResponse.Success -> apiResponse.value
            }
        }
    }

    @Test
    fun `repository sync should persist ApiResponse`() = runTest(testDispatcher) {
        //When
        synchronizer.usersSync(
            ioDispatcher = testDispatcher,
            usersFetcher = usersRepository::getRemoteUsers,
            usersPersistence = usersRepository::insertLocalUsers
        )

        val dbUsers: List<UserModel> = localDataSource.getUsers().first()
        val remoteUsers = fetchUsers()?.toDomainModel(imageProcessor, testDispatcher)

        // Then
        assertEquals(dbUsers, remoteUsers)
    }

    @Test
    fun `searchUser should return filtered and sorted users`() = runTest {
        // When
        localDataSource.users = populatedListOfUsers
        val result = usersRepository.searchUser(
            searchQuery = "Annabelle",
            sortColumn = SortBy.NAME.parameter,
            sortOrder = OrderDirection.ASCENDING.parameter
        ).first()

        // Then
        assertEquals(1, result.size)
        assertEquals("Annabelle Reilly", result.first().name)
    }

    @Test
    fun `getLocalUsers should return all local users`() = runTest {
        // When
        localDataSource.users = populatedListOfUsers
        val result = usersRepository.getLocalUsers().first()

        // Then
        assertEquals(fakeUserModelList.size, result.size)
    }

    @Test
    fun `insertLocalUser should add a user to the local data source`() = runTest(testDispatcher) {
        // Given
        val newUserResponse = UserResponse(
            id = 7,
            name = "New User",
            img = "https://randomuser.me/api/portraits/men/7.jpg",
            username = "newuser"
        )

        // When
        usersRepository.insertLocalUser(newUserResponse)

        // Then
        val localUsers = localDataSource.getUsers().first()
        assertTrue(localUsers.any { it.externalId == 7 && it.name == "New User" })
    }

    @Test
    fun `getRemoteUsers should return all remote users`() = runTest {
        // When
        localDataSource.users = populatedListOfUsers
        val result = usersRepository.getRemoteUsers()

        // Then
        assertTrue(result is ApiResponse.Success)
        assertEquals(fakeUserResponseList.size, (result as ApiResponse.Success).value.size)
    }

    @Test
    fun `insertLocalUsers should add multiple users to the local data source`() = runTest(testDispatcher) {
        // Given
        val newUsersResponse = listOf(
            UserResponse(
                id = 7,
                name = "New User 1",
                img = "https://randomuser.me/api/portraits/men/7.jpg",
                username = "newuser1"
            ),
            UserResponse(
                id = 8,
                name = "New User 2",
                img = "https://randomuser.me/api/portraits/men/8.jpg",
                username = "newuser2"
            )
        )

        // When
        usersRepository.insertLocalUsers(*newUsersResponse.toTypedArray())

        // Then
        val localUsers = localDataSource.getUsers().first()
        assertTrue(localUsers.any { it.externalId == 7 && it.name == "New User 1" })
        assertTrue(localUsers.any { it.externalId == 8 && it.name == "New User 2" })
    }
}