package com.picpay.desafio.android.feature.contacts.components.searchbar.sortmenu

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import com.picpay.desafio.android.core.data.util.OrderDirection
import com.picpay.desafio.android.core.data.util.SortBy
import com.picpay.desafio.android.feature.contacts.viewmodel.ContactUiEvent
import com.picpay.desafio.android.feature.contacts.viewmodel.EventOrderChange
import com.picpay.desafio.android.feature.contacts.viewmodel.EventSortChange
import com.picpay.desafio.android.feature.contacts.viewmodel.SearchUiState

@Composable
fun RowScope.OrderDirectionMenu(
    modifier: Modifier = Modifier,
    isExpanded: MutableState<Boolean>,
    searchUiState: SearchUiState,
    onEvent: (ContactUiEvent) -> Unit
) {
    BaseContactSortDropDownMenu(
        modifier = modifier,
        isExpanded = isExpanded,
        menuLabel = "Ordem:",
        selectedItemText = searchUiState.orderDirection.displayText,
        items = OrderDirection.entries.toTypedArray(),
        selectedItem = searchUiState.orderDirection,
        onSortingEvent = { sortingOption ->
            when (sortingOption) {
                is OrderDirection -> onEvent(EventOrderChange(sortingOption))
                is SortBy -> onEvent(EventSortChange(sortingOption))
            }
        }
    )
}