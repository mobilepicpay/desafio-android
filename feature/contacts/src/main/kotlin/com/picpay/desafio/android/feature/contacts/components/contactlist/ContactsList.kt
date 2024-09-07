package com.picpay.desafio.android.feature.contacts.components.contactlist

import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.picpay.desafio.android.feature.contacts.R
import com.picpay.desafio.android.feature.contacts.viewmodel.ContactsScreenUiState
import kotlinx.coroutines.delay

@Composable
fun ContactsList(
    uiState: ContactsScreenUiState,
    searchString: String
) {
    val focusManager = LocalFocusManager.current
    val interaction = remember { MutableInteractionSource() }

    var lastFirstItem by rememberSaveable { mutableIntStateOf(0) }

    val listState = rememberLazyListState()
    LaunchedEffect(
        uiState.searchUiState.searchQuery,
        uiState.searchUiState.sortedBy,
        uiState.searchUiState.orderDirection,
    ) {
        delay(50) // Time for list recompose before trying to scroll
        listState.animateScrollToItem(0)
    }

    LaunchedEffect(Unit) {
        delay(55) // Time for list recompose before trying to scroll
        listState.animateScrollToItem(lastFirstItem)
    }
    // Workaround to save scroll position during configuration changes
    if (listState.isScrollInProgress) {
        lastFirstItem = remember { derivedStateOf { listState.firstVisibleItemIndex } }.value
    }

    LazyColumn(
        state = listState,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        itemsIndexed(
            items = uiState.filteredUsers,
            key = { _, it -> it.externalId }
        ) { index, user ->

            ContactListItem(
                modifier = Modifier
                    .animateItem(placementSpec = tween())
                    .clickable(
                        interactionSource = interaction,
                        indication = null
                    ) { focusManager.clearFocus() },
                user = user,
                searchString = searchString
            )

            if (index == uiState.filteredUsers.size - 1) {
                Spacer(modifier = Modifier.height(24.dp))

                // TRANSPARENT - FOR UI TEST ONLY
                Text(stringResource(R.string.all_items_are_loaded), color = Color.Transparent)
                // TRANSPARENT - FOR UI TEST ONLY
            }
        }
    }
}