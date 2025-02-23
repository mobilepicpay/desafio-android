package com.picpay.desafio.android.presentation.ui.utils.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.picpay.desafio.android.R
import com.picpay.desafio.android.domain.utils.ErrorInformation

const val TEXT_TAG = "text_tag"

@Composable
fun CacheAlertComposable(
    error: ErrorInformation,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
        colors = CardColors(
            Color.Transparent,
            Color.Transparent,
            Color.Transparent,
            Color.Transparent,
        ),
        border = BorderStroke(
            width = 1.dp,
            color = colorResource(R.color.colorAccent)
        )
    ) {
        Text(
            modifier = Modifier
                .padding(8.dp)
                .testTag(TEXT_TAG),
            text = error.message.orEmpty(),
            color = Color.White,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            style = TextStyle()
        )
    }
}

@Composable
@Preview
private fun CacheAlertPreview() {
    CacheAlertComposable(
        modifier = Modifier.padding(top = 12.dp),
        error = ErrorInformation(
            stringResource(R.string.errorWithCache)
        )
    )
}