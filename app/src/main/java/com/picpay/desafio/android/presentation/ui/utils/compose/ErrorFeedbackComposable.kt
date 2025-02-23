package com.picpay.desafio.android.presentation.ui.utils.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.picpay.desafio.android.R
import com.picpay.desafio.android.presentation.ui.features.contacts.DummyContactsListener
import com.picpay.desafio.android.presentation.ui.utils.FeedbackListener

const val ERROR_TEXT_TAG = "errorText"
const val ERROR_BUTTON_TAG = "errorButton"

@Composable
fun ErrorFeedbackComposable(
    errorMessage: String = stringResource(R.string.error),
    buttonTitle: String = stringResource(R.string.reload),
    listener: FeedbackListener,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterVertically),
    ) {
        Text(
            modifier = Modifier.testTag(ERROR_TEXT_TAG),
            text = errorMessage,
            color = Color.White,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
        )

        Button(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .testTag(ERROR_BUTTON_TAG),
            colors = ButtonColors(
                containerColor = colorResource(R.color.colorAccent),
                contentColor = Color.White,
                disabledContentColor = colorResource(R.color.colorDetail),
                disabledContainerColor = colorResource(R.color.colorPrimary),
            ),
            shape = RoundedCornerShape(8.dp),
            onClick = {
                listener.onButtonClicked()
            },
        ) {
            Text(
                text = buttonTitle,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Composable
@Preview
private fun ErrorFeedbackComposablePreview() {
    ErrorFeedbackComposable(
        modifier = Modifier.fillMaxSize(),
        listener = DummyContactsListener
    )
}