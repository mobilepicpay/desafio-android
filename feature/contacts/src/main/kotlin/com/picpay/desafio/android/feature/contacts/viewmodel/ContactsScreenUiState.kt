package com.picpay.desafio.android.feature.contacts.viewmodel

import com.picpay.desafio.android.core.model.UserModel

data class ContactsScreenUiState(
    var searchUiState: SearchUiState = SearchUiState(),
    var isSyncing: Boolean = false,
    var isNetworkAvailable: Boolean? = null,
    var displayMessage: String? = null,
    var users: List<UserModel> = emptyList(),
    var filteredUsers: List<UserModel> = emptyList(),
    var userListIsStored: Boolean = false
) {
    private val isOfflineAndUsersIsEmpty =
        users.isEmpty() && isNetworkAvailable == false

    private val isSyncingButUsersIsEmpty =
        isSyncing && users.isEmpty()

    private val noResultsOnSearch =
        searchUiState.searchQuery.isNotEmpty() &&
                filteredUsers.isEmpty() && users.isNotEmpty()

    val condition: ContactsUiStateCondition
        get() = when {
            isOfflineAndUsersIsEmpty -> NoInternet
            isSyncingButUsersIsEmpty -> IsLoading
            noResultsOnSearch -> NoResultsOnSearch
            else -> ShowContactList
        }
}

sealed interface ContactsUiStateCondition
data object NoInternet : ContactsUiStateCondition
data object IsLoading : ContactsUiStateCondition
data object ShowContactList : ContactsUiStateCondition
data object NoResultsOnSearch : ContactsUiStateCondition