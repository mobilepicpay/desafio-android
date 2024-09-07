package com.picpay.desafio.android.feature.contacts.components.searchbar.sortmenu

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.picpay.desafio.android.ui.theme.picPayGreen

@Composable
fun SortMenuButtonMask(
    isMenuExpanded: Boolean,
    label: String,
    selectedItemText: String,
    onClick: () -> Unit,
) {
    val buttonTextColor = Color.White
    val buttonTextFontSize = 14.sp

    val buttonLabelFontSize = 12.sp

    val color by animateColorAsState(
        targetValue = if (isMenuExpanded) picPayGreen else Color.Gray
    )

    val borderWidth by animateDpAsState(
        targetValue = if (isMenuExpanded) 2.dp else 1.dp
    )

    Card(
        border = BorderStroke(borderWidth, color),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .clickable { onClick() },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = label,
                fontSize = buttonLabelFontSize,
                color = color
            )

            Text(
                text = selectedItemText,
                color = buttonTextColor,
                fontSize = buttonTextFontSize
            )
        }
    }
}