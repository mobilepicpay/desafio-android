package com.picpay.desafio.android.feature.contacts.components.searchbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.picpay.desafio.android.feature.contacts.R
import com.picpay.desafio.android.feature.contacts.viewmodel.ContactUiEvent
import com.picpay.desafio.android.feature.contacts.viewmodel.EventSearchChange
import com.picpay.desafio.android.ui.theme.colorDetail
import com.picpay.desafio.android.ui.theme.picPayGreen

@Composable
fun RowScope.ContactsSearchField(
    modifier: Modifier = Modifier,
    isSearchFieldFocused: Boolean,
    onSearchFieldFocusChange: (Boolean) -> Unit,
    search: String,
    onEvent: (ContactUiEvent) -> Unit
) {
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = search,
        textStyle = TextStyle(color = Color.White),
        onValueChange = { onEvent(EventSearchChange(it)) },
        modifier = modifier then Modifier
            .height(70.dp)
            .padding(bottom = 12.dp)
            .onFocusChanged { focusState -> onSearchFieldFocusChange(focusState.isFocused) },
        shape = RoundedCornerShape(10.dp),
        colors = searchFieldColors(),
        placeholder = { SearchFieldPlaceholder() },
        trailingIcon = { SearchFieldTrailingIcon(search, isSearchFieldFocused, onEvent) },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = { focusManager.clearFocus() }),
        singleLine = true
    )
}

@Composable
private fun SearchFieldTrailingIcon(
    search: String,
    isSearchFieldFocused: Boolean,
    onEvent: (ContactUiEvent) -> Unit
) {
    if (search.isNotEmpty() && isSearchFieldFocused) {
        Card(
            colors = CardDefaults.cardColors(containerColor = Color.DarkGray),
            modifier = Modifier
                .clickable { onEvent(EventSearchChange("")) }
        ) {
            Icon(
                imageVector = Icons.Default.Clear,
                contentDescription = "clear search",
                tint = Color.Gray
            )
        }
    }
}

@Composable
private fun SearchFieldPlaceholder() {
    Text(
        text = stringResource(R.string.contacts_searchfield_placeholder),
        color = colorDetail
    )
}

@Composable
fun searchFieldColors() = TextFieldDefaults.colors(
    focusedContainerColor = Color.Transparent,
    unfocusedContainerColor = Color.Transparent,
    disabledContainerColor = Color.Transparent,
    focusedIndicatorColor = picPayGreen,
    unfocusedIndicatorColor = colorDetail,
    cursorColor = picPayGreen,
)