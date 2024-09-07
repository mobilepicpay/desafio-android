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

class LocalUsersFlowUseCaseTest: KoinTest {

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(
            testingDomainModule,
            testingDataModule
        )
    }

    private val testDispatcher: TestDispatcher by inject()

    private val searchLocalUsersFlowUseCase: LocalUsersFlowUseCase by inject()

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
    fun `invoke return correct list`() = runTest(testDispatcher) {
        // Given
        localDataSource.users = populatedListOfUsers

        // When
        val result =
            searchLocalUsersFlowUseCase().first()

        // Then
        val expectedResult = fakeUserModelList
        assertEquals(expectedResult, result)
    }
}
