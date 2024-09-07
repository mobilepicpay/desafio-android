package com.picpay.desafio.android.feature.contacts

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.picpay.desafio.android.feature.contacts.components.ContactsScreenTitle
import com.picpay.desafio.android.feature.contacts.components.NoInternetAlertWithEasterEgg
import com.picpay.desafio.android.feature.contacts.components.contactlist.ContactsList
import com.picpay.desafio.android.feature.contacts.components.searchbar.ContactsSearchBar
import com.picpay.desafio.android.feature.contacts.viewmodel.ContactUiEvent
import com.picpay.desafio.android.feature.contacts.viewmodel.ContactsScreenUiState
import com.picpay.desafio.android.feature.contacts.viewmodel.ContactsScreenViewModel
import com.picpay.desafio.android.feature.contacts.viewmodel.EventSearchChange
import com.picpay.desafio.android.feature.contacts.viewmodel.IsLoading
import com.picpay.desafio.android.feature.contacts.viewmodel.NoInternet
import com.picpay.desafio.android.feature.contacts.viewmodel.NoResultsOnSearch
import com.picpay.desafio.android.feature.contacts.viewmodel.ShowContactList
import com.picpay.desafio.android.ui.util.showShortToast
import com.picpay.desafio.android.ui.widgets.ProgressIndicator
import com.picpay.desafio.android.ui.widgets.alerts.NoResultsOnSearchAlert
import org.koin.androidx.compose.koinViewModel

@Composable
fun ContactScreen(
    launchGame: () -> Unit
) {
    val viewModel: ContactsScreenViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsState()

    ContactsScreenContent(
        uiState = uiState,
        launchGame = launchGame,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun ContactsScreenContent(
    uiState: ContactsScreenUiState,
    launchGame: () -> Unit,
    onEvent: (ContactUiEvent) -> Unit
) {
    val context = LocalContext.current
    LaunchedEffect(uiState.isSyncing) {
        if (uiState.isSyncing) showShortToast(R.string.synchronizing, context)
    }

    Column(
        Modifier
            .animateContentSize()
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {
        var isSearchFieldFocused by remember { mutableStateOf(false) }

        val titleIsHidden =
            isSearchFieldFocused || uiState.searchUiState.searchQuery.isNotEmpty()

        AnimatedVisibility(!titleIsHidden) {
            ContactsScreenTitle()
        }

        var searchString by rememberSaveable { mutableStateOf("") }
        ContactsSearchBar(
            search = searchString,
            searchUiState = uiState.searchUiState,
            isSearchFieldFocused = isSearchFieldFocused,
            onSearchFieldFocusChange = { isSearchFieldFocused = it },
            onEvent = { event ->
                when (event) {
                    is EventSearchChange -> {
                        searchString = event.search
                        onEvent(event)
                    }
                    else -> onEvent(event)
                }
            }
        )

        when (uiState.condition) {
            NoInternet -> NoInternetAlertWithEasterEgg { launchGame() }
            IsLoading -> ProgressIndicator(Modifier.fillMaxSize())
            NoResultsOnSearch -> NoResultsOnSearchAlert()
            ShowContactList -> {
                ContactsList(
                    uiState = uiState,
                    searchString = searchString
                )
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
private fun ContactsScreenContentPreview() {
    ContactsScreenContent(
        uiState = ContactsScreenUiState(),
        launchGame = { },
        onEvent = { }
    )
}