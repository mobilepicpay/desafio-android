package com.picpay.desafio.contact_list.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.picpay.desafio.contact_list.presentation.components.Loading
import com.picpay.desafio.contact_list.presentation.viewmodel.ContactUiError
import com.picpay.desafio.contact_list.presentation.viewmodel.ContactUiState
import com.picpay.desafio.contact_list.presentation.viewmodel.ContactUserEvent
import com.picpay.desafio.contact_list.presentation.viewmodel.ContactViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun ContactScreen(viewModel: ContactViewModel = getViewModel()) {

    val uiState = viewModel.uiState.collectAsState().value

    LaunchedEffect(key1 = Unit, block = {
        if(uiState.userList == null && uiState.error == null)
        viewModel.setUserEvent(ContactUserEvent.OnInitScreen)
    })

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .testTag("ContactListScreen"), color = MaterialTheme.colors.primaryVariant
    ) {
        ContactScreenContent(uiState = uiState)
    }
}

@Composable
private fun ContactScreenContent(uiState: ContactUiState) {
    if (uiState.error != null) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = getErrorMessage(uiState.error),
                fontSize = 28.sp,
                color = Color.Red,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.testTag("ContactListError")
            )
        }
    } else {
        Column {
            Text(
                text = stringResource(id = R.string.contacts),
                fontSize = 28.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 48.dp, start = 24.dp, bottom = 24.dp)
                    .testTag("ContactListTitle")
            )
            if (uiState.loading) {
                Loading()
            } else {
                LazyColumn(content = {
                    items(items = uiState.userList ?: listOf()) { item ->
                        ContactItem(imageUrl = item.img, userName = item.username, name = item.name)
                    }
                }, modifier = Modifier.testTag("ContactList"))
            }
        }
    }
}

@Composable
private fun getErrorMessage(uiError: ContactUiError): String {
    return when (uiError) {
        ContactUiError.InternetConnection -> stringResource(id = R.string.internet_connection_error)
        ContactUiError.UnknownError -> stringResource(id = R.string.unknown_error)
    }
}
