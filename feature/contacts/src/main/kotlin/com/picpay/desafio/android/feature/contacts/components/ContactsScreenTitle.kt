package com.picpay.desafio.android.feature.contacts.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.picpay.desafio.android.feature.contacts.R

@Composable
fun ContactsScreenTitle() {

    val focusManager = LocalFocusManager.current
    val interaction = remember { MutableInteractionSource() }

    Text(
        modifier = Modifier
            .padding(vertical = 21.dp)
            .fillMaxWidth()
            .clickable(
                interactionSource = interaction,
                indication = null
            ) { focusManager.clearFocus() },
        text = stringResource(R.string.contacts_screen_list_header),
        fontSize = 28.sp,
        color = Color.White,
        fontWeight = FontWeight.Bold
    )
}