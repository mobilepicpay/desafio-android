package com.picpay.desafio.android.feature.contacts.viewmodel

import com.picpay.desafio.android.core.data.network.test.TestNetworkMonitor
import com.picpay.desafio.android.core.data.util.OrderDirection
import com.picpay.desafio.android.core.data.util.SortBy
import com.picpay.desafio.android.feature.contacts.di.test.testingFeatureContactsModule
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import kotlin.test.Test
import kotlin.test.assertEquals

class ContactsScreenViewModelTest: KoinTest {

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(testingFeatureContactsModule)
    }


    private val  viewModel: ContactsScreenViewModel by inject()
    private val testDispatcher: TestDispatcher by inject()
    private val networkMonitor: TestNetworkMonitor by inject()

    @Test
    fun `initial uiState is correct`() = runTest(testDispatcher) {
        //Given
        val expectedState = ContactsScreenUiState(
            isNetworkAvailable = true,
            filteredUsers = emptyList(),
            isSyncing = false,
            searchUiState = SearchUiState()
        )
        backgroundScope.launch {
            networkMonitor.setConnected(true)

            // Then
            assertEquals(expectedState, viewModel.uiState.value)
        }
    }

    @Test
    fun `onEvent updates order direction`() = runTest(testDispatcher) {
        //Given
        val newOrder = OrderDirection.DESCENDING

        backgroundScope.launch {
            // When
            viewModel.onEvent(EventOrderChange(newOrder))

            // Then
            val expectedState = SearchUiState(orderDirection = newOrder)
            assertEquals(expectedState, viewModel.uiState.value.searchUiState)
        }
    }

    @Test
    fun `onEvent updates sort column`() = runTest(testDispatcher) {
        // Given
        val newSortBy = SortBy.USERNAME

        backgroundScope.launch {
            // When
            viewModel.onEvent(EventSortChange(newSortBy))

            // Then
            val expectedState = SearchUiState(sortedBy = newSortBy)
            assertEquals(expectedState, viewModel.uiState.value.searchUiState)
        }
    }

    @Test
    fun `onEvent updates search query`() =runTest(testDispatcher) {
        // Given
        val searchQuery = "@john"

        backgroundScope.launch {
            // When
            viewModel.onEvent(EventSearchChange(searchQuery))

            // Then
            val expectedState = SearchUiState(searchQuery = "john")
            assertEquals(expectedState, viewModel.uiState.value.searchUiState)
        }
    }
}