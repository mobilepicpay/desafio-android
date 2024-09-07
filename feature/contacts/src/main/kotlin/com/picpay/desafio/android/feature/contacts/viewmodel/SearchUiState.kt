package com.picpay.desafio.android.feature.contacts.viewmodel

import com.picpay.desafio.android.core.data.util.OrderDirection
import com.picpay.desafio.android.core.data.util.SortBy

data class SearchUiState(
    var searchQuery: String = "",
    var sortedBy: SortBy = SortBy.NAME,
    var orderDirection: OrderDirection = OrderDirection.ASCENDING
)


sealed interface ContactUiEvent
data class EventSearchChange(val search: String): ContactUiEvent
data class EventOrderChange(val orderDirection: OrderDirection): ContactUiEvent
data class EventSortChange(val sortBy: SortBy): ContactUiEvent
