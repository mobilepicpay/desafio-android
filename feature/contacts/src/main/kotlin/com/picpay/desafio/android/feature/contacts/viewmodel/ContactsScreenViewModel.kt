package com.picpay.desafio.android.feature.contacts.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.core.data.network.NetworkMonitor
import com.picpay.desafio.android.core.data.sync.SyncManager
import com.picpay.desafio.android.core.domain.usecase.LocalUsersFlowUseCase
import com.picpay.desafio.android.core.domain.usecase.LocalUsersFlowUseCaseImpl
import com.picpay.desafio.android.core.domain.usecase.SearchLocalUsersFlowUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

@OptIn(ExperimentalCoroutinesApi::class)
class ContactsScreenViewModel(
    syncManager: SyncManager,
    searchLocalUsersFlowUseCase: SearchLocalUsersFlowUseCase,
    localUsersFlowUseCaseImpl: LocalUsersFlowUseCase,
    networkMonitor: NetworkMonitor
) : ViewModel() {

    private var searchUiState = MutableStateFlow(SearchUiState())

    private val usersFilteredBySearch = searchUiState.flatMapLatest { state ->
        searchLocalUsersFlowUseCase(
            searchQuery = state.searchQuery,
            sortColumn = state.sortedBy.parameter,
            sortOrder = state.orderDirection.parameter
        )
    }

    val uiState = combine(
        localUsersFlowUseCaseImpl(),
        usersFilteredBySearch,
        networkMonitor.isOnline,
        syncManager.isSyncing,
        searchUiState
    ) {
        localUsers,
        usersFiltered,
        isOnline,
        isSyncing,
        searchUiState ->
        ContactsScreenUiState(
            isNetworkAvailable = isOnline,
            users = localUsers,
            filteredUsers = usersFiltered,
            isSyncing = isSyncing,
            searchUiState = searchUiState
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        ContactsScreenUiState()
    )

    fun onEvent(event: ContactUiEvent) {
        when(event) {
            is EventOrderChange -> searchUiState
                .update { it.copy(orderDirection = event.orderDirection) }

            is EventSortChange -> searchUiState
                .update { it.copy(sortedBy = event.sortBy) }

            is EventSearchChange -> searchUiState
                .update { it.copy(searchQuery = event.search.run { removePrefix("@") }) }
        }
    }
}