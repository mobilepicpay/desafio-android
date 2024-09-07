package com.picpay.desafio.android.feature.contacts.components.searchbar.sortmenu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import com.picpay.desafio.android.core.data.util.SortBy
import com.picpay.desafio.android.core.data.util.SortingOption

@Composable
fun BaseContactSortDropDownMenu(
    modifier: Modifier = Modifier,
    isExpanded: MutableState<Boolean>,
    menuLabel: String,
    selectedItemText: String,
    onSortingEvent: (SortingOption) -> Unit,
    items: Array<SortingOption>,
    selectedItem: SortingOption
) {
    val focusManager = LocalFocusManager.current

    Box(modifier, contentAlignment = Alignment.Center) {
        SortMenuButtonMask(
            isMenuExpanded = isExpanded.value,
            label = menuLabel,
            selectedItemText = selectedItemText,
            onClick = {
                focusManager.clearFocus()
                isExpanded.value = true
            }
        )

        DropdownMenu(
            modifier = Modifier.background(Color.DarkGray),
            expanded = isExpanded.value,
            onDismissRequest = { isExpanded.value = false }
        ) {
            items.forEachIndexed { index, item ->
                BaseContactSortMenuItem(
                    isSelected = item == selectedItem,
                    displayText = item.displayText,
                    onClick = {
                        onSortingEvent(item)
                        isExpanded.value = false
                    }
                )

                if (index != SortBy.entries.size - 1) {
                    HorizontalDivider(color = Color.Gray)
                }
            }
        }
    }
}