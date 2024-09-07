package com.picpay.desafio.android.feature.contacts.components

import androidx.compose.runtime.Composable
import com.picpay.desafio.android.ui.widgets.ClickableCounterBox
import com.picpay.desafio.android.ui.widgets.alerts.NoInternetAlert

@Composable
fun NoInternetAlertWithEasterEgg(
    launchGame: () -> Unit
) {
    ClickableCounterBox(
        finalClickCall = { launchGame() },
        content =  { NoInternetAlert() }
    )
}