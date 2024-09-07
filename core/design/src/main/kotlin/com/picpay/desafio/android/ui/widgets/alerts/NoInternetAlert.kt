package com.picpay.desafio.android.ui.widgets.alerts

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.picpay.desafio.android.core.design.R

@Composable
fun NoInternetAlert(
    modifier: Modifier = Modifier
) =
    BaseAlert(
        modifier = modifier,
        iconId = R.drawable.no_internet,
        iconDescription = stringResource(R.string.no_internet_image),
        optionalAlert = {  },
        alertText = stringResource(R.string.no_internet_alert_text)
    )

@Preview
@Composable
private fun NoInternetAlertPreview() {
    NoInternetAlert()
}