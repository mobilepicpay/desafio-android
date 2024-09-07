package com.picpay.desafio.android.core.domain.usecase

import com.picpay.desafio.android.core.data.di.test.testingDataModule

import com.picpay.desafio.android.core.data.repository.fake.FakeUserLocalDataSource
import com.picpay.desafio.android.core.data.repository.fake.lists.fakeUserModelList
import com.picpay.desafio.android.core.data.util.OrderDirection
import com.picpay.desafio.android.core.data.util.SortBy
import com.picpay.desafio.android.core.domain.di.testingDomainModule
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject

class SearchLocalUsersFlowUseCaseTest: KoinTest {

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(
            testingDomainModule,
            testingDataModule
        )
    }

    private val testDispatcher: TestDispatcher by inject()

    private val searchLocalUsersFlowUseCase: SearchLocalUsersFlowUseCase by inject()

    private val localDataSource: FakeUserLocalDataSource by inject()


    private val populatedListOfUsers = fakeUserModelList.toMutableList()


    @Before
    fun setUp() {
        localDataSource.users = populatedListOfUsers
    }

    @After
    fun cleanUp() {
        localDataSource.users = mutableListOf()
    }

    @Test
    fun `invoke searches and sorts users correctly`() = runTest(testDispatcher) {
        // Given
        localDataSource.users = populatedListOfUsers
        val searchQuery = "John"
        val sortColumn = SortBy.NAME.parameter
        val sortOrder = OrderDirection.ASCENDING.parameter

        // When
        val result =
            searchLocalUsersFlowUseCase(searchQuery, sortColumn, sortOrder).first()

        // Then
        val expectedResult =
            fakeUserModelList.filter { it.name.contains(searchQuery, ignoreCase = true) }
                .sortedBy { it.name } // Assuming sorting by name in ascending order
        assertEquals(expectedResult, result)
    }

    @Test
    fun `invoke returns empty list when no users match the search query`() = runTest(testDispatcher) {
        // Given
        val searchQuery = "NonExistingUser"
        val sortColumn = SortBy.NAME.parameter
        val sortOrder = OrderDirection.ASCENDING.parameter

        // When
        val result = searchLocalUsersFlowUseCase(searchQuery, sortColumn, sortOrder).first()

        // Then
        assertTrue(result.isEmpty())
    }

    @Test
    fun `invoke sorts users by username correctly`() = runTest(testDispatcher) {
        // Given
        val searchQuery = ""
        val sortColumn = SortBy.USERNAME.parameter
        val sortOrder = OrderDirection.ASCENDING.parameter

        // When
        val result = searchLocalUsersFlowUseCase(searchQuery, sortColumn, sortOrder).first()

        // Then
        val expectedResult =
            if (sortOrder == OrderDirection.ASCENDING.parameter) {
                fakeUserModelList.sortedBy { it.username }
            } else {
                fakeUserModelList.sortedByDescending { it.username }
            }

        assertEquals(expectedResult, result)
    }

    @Test
    fun `invoke sorts users in descending order correctly`() = runTest(testDispatcher) {
        // Given
        val searchQuery = "" // Empty search query to include all users
        val sortColumn = SortBy.NAME.parameter
        val sortOrder = OrderDirection.DESCENDING.parameter

        // When
        val result = searchLocalUsersFlowUseCase(searchQuery, sortColumn, sortOrder).first()

        // Then
        val expectedResult =
            if (sortOrder == OrderDirection.ASCENDING.parameter) {
                fakeUserModelList.sortedBy { it.name }
            } else {
                fakeUserModelList.sortedByDescending { it.name }
            }

        assertEquals(expectedResult, result)
    }

    @Test
    fun `invoke performs case-insensitive search`() = runTest(testDispatcher) {
        // Given
        val searchQuery = "SANDRINE" // Uppercase version of "Sandrine"
        val sortColumn = SortBy.NAME.parameter
        val sortOrder = OrderDirection.ASCENDING.parameter

        // When
        val result = searchLocalUsersFlowUseCase(searchQuery, sortColumn, sortOrder).first()

        // Then
        val expectedResult =
            if (sortOrder == OrderDirection.ASCENDING.parameter) {
                fakeUserModelList.filter { it.name.contains(searchQuery, ignoreCase = true) }
                    .sortedBy { it.name }
            } else {
                fakeUserModelList.filter { it.name.contains(searchQuery, ignoreCase = true) }
                    .sortedByDescending { it.name }
            }

        assertEquals(expectedResult, result)
    }

    @Test
    fun `invoke handles special characters in search query`() = runTest(testDispatcher) {
        // Given
        val searchQuery = "Ms. Simeon Yost" // Exact match
        val sortColumn = SortBy.NAME.parameter
        val sortOrder = OrderDirection.ASCENDING.parameter

        // When
        val result = searchLocalUsersFlowUseCase(searchQuery, sortColumn, sortOrder).first()

        // Then
        val expectedResult =
            if (sortOrder == OrderDirection.ASCENDING.parameter) {
                fakeUserModelList.filter { it.name == searchQuery }
                    .sortedBy { it.name }
            } else {
                fakeUserModelList.filter { it.name == searchQuery }
                    .sortedByDescending { it.name }
            }

        assertEquals(expectedResult, result)
    }
}
