package com.picpay.desafio.android.presentation.ui.utils.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.picpay.desafio.android.R
import com.picpay.desafio.android.domain.utils.State
import com.picpay.desafio.android.presentation.ui.features.contacts.compose.CACHE_TAG
import com.picpay.desafio.android.presentation.ui.features.contacts.compose.ERROR_TAG
import com.picpay.desafio.android.presentation.ui.features.contacts.compose.LOADING_IMAGE_TAG
import com.picpay.desafio.android.presentation.ui.features.contacts.compose.LOADING_TAG
import com.picpay.desafio.android.presentation.ui.utils.FeedbackListener

@Composable
fun ScreenComposable(
    screenState: State<Any> = State.Loading,
    listener: FeedbackListener,
    toolbar: @Composable () -> Unit,
    loadingContent: @Composable () -> Unit = {
        LoadingGeneric(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
        )
    },
    errorContent: @Composable () -> Unit = {
        ErrorGeneric(
            screenState = screenState,
            listener = listener
        )
    },
    successContent: @Composable () -> Unit,
) {
    Scaffold(
        containerColor = colorResource(R.color.colorPrimaryDark)
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            toolbar()

            val errorWithoutCache = screenState is State.Error

            if (errorWithoutCache) {
                errorContent()
            } else if (screenState is State.Loading) {
                loadingContent()
            } else {
                (screenState as? State.ErrorWithCache)?.let { errorWithCache ->
                    CacheAlertComposable(
                        modifier = Modifier
                            .padding(
                                top = 12.dp,
                                start = 24.dp,
                                end = 24.dp,
                            )
                            .testTag(CACHE_TAG)
                            .clickable { listener.onButtonClicked() },
                        error = errorWithCache.error
                    )
                }

                successContent()
            }
        }
    }
}

@Composable
private fun LoadingGeneric(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .testTag(LOADING_TAG),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(48.dp)
                .testTag(LOADING_IMAGE_TAG),
            color = colorResource(R.color.colorAccent)
        )
    }
}

@Composable
private fun ErrorGeneric(
    screenState: State<Any>,
    listener: FeedbackListener,
) {
    val state = screenState as? State.Error
    ErrorFeedbackComposable(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .testTag(ERROR_TAG),
        errorMessage = state?.error?.message.orEmpty(),
        listener = listener,
    )
}