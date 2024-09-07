package com.picpay.desafio.android.feature.contacts.components.searchbar.sortmenu

import androidx.compose.foundation.background
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.picpay.desafio.android.ui.theme.picPayGreen

@Composable
fun BaseContactSortMenuItem(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    onClick: () -> Unit,
    displayText: String
) {
    DropdownMenuItem(
        modifier = modifier then Modifier
            .background(Color.DarkGray),
        colors = MenuDefaults.itemColors(
            textColor =
            if (isSelected) picPayGreen
            else Color.White
        ),
        onClick = { onClick() },
        text = { Text(displayText) }
    )
}