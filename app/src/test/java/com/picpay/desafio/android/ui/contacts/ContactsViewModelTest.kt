package com.picpay.desafio.android.ui.contacts

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.data.UserRepository
import com.picpay.desafio.android.data.coroutine.DispatcherProvider
import com.picpay.desafio.android.data.database.UserDatabaseRepository
import com.picpay.desafio.android.data.model.User
import com.picpay.desafio.android.data.model.UserUiData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.koin.dsl.module
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import kotlin.test.assertFalse

class ContactsViewModelTest {

    private lateinit var sut: ContactsViewModel

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val userRepositoryMocked = mock<UserRepository>()
    private val userDatabaseRepositoryMocked = mock<UserDatabaseRepository>()

    @get:Rule
    val koinRule = KoinTestRule(
        module {
            single { userRepositoryMocked }
            single { userDatabaseRepositoryMocked }
            single<DispatcherProvider> { TestDispatcherProvider() }
        }
    )

    @Before
    fun setup() {
        sut = ContactsViewModel()
    }

    @Test
    fun `on init, assert loading is false`() {
        assertFalse(sut.isLoading.value)
    }

    @Test
    fun `on init, assert usersUiData is emptyList`() {
        assertEquals(emptyList<UserUiData>(), sut.usersUiData.value)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `on getUsers, when endpoint returns success, assert that usersUiData is set`() = runTest {
        whenever(userDatabaseRepositoryMocked.getUsers()).thenReturn(emptyList())

        val usersMocked = listOf(
            User(
                1,
                "username",
                "name",
                null
            ),
            User(
                2,
                "username",
                "name",
                null
            )
        )

        val expectedResult = listOf(
            UserUiData(
                1,
                "username",
                "name",
                null
            ),
            UserUiData(
                2,
                "username",
                "name",
                null
            )
        )

        backgroundScope.launch(UnconfinedTestDispatcher()) { sut.users.collect() }
        backgroundScope.launch(UnconfinedTestDispatcher()) { sut.usersUiData.collect() }

        whenever(userRepositoryMocked.getUsers()).thenReturn(usersMocked)

        sut.getUsers()

        assertEquals(expectedResult, sut.usersUiData.value)
        verify(userDatabaseRepositoryMocked, times(1)).getUsers()
    }

//    @OptIn(ExperimentalCoroutinesApi::class)
//    @Test
//    fun `on getUsers, when endpoint returns failure and no cache data, assert that usersUiData is set`() = runTest {
//        backgroundScope.launch(UnconfinedTestDispatcher()) { sut.users.collect() }
//        backgroundScope.launch(UnconfinedTestDispatcher()) { sut.usersUiData.collect() }
//
//        whenever(userRepositoryMocked.getUsers()).thenThrow(HttpException(mock()))
//        whenever(userDatabaseRepositoryMocked.getUsers()).thenReturn(emptyList())
//
//        sut.getUsers()
//
//        assertEquals(emptyList<UserUiData>(), sut.usersUiData.value)
//        verify(userDatabaseRepositoryMocked, never()).getUsers()
//    }
}