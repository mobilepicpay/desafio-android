package com.picpay.desafio.android.ui.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.picpay.desafio.android.core.design.R

@Composable
fun ProgressIndicator(modifier: Modifier = Modifier) {
    Box(
        modifier, contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = Color.Green)

        // TRANSPARENT - FOR UI TEST ONLY
        Text(text = stringResource(R.string.circularprogressindicator_text), color = Color.Transparent)
        // TRANSPARENT - FOR UI TEST ONLY

    }
}