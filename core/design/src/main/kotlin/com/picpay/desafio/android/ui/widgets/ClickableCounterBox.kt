package com.picpay.desafio.android.ui.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun ClickableCounterBox(
    initialClicks: Int = 3,
    maxNumberSize: Int = 100,
    animationDuration: Int = 500,
    finalClickCall: () -> Unit,
    content: @Composable () -> Unit
) {
    var numberOfRemainingClicks by remember { mutableIntStateOf(initialClicks) }
    var isNumberVisible by remember { mutableStateOf(false) }

    val animatedSize by animateIntAsState(
        targetValue = if (isNumberVisible) maxNumberSize else 0,
        animationSpec = tween(durationMillis = animationDuration),
        finishedListener = {
            isNumberVisible = false
            numberOfRemainingClicks--
        }
    )

    val interaction = remember { MutableInteractionSource() }

    Box(
        modifier = Modifier
            .wrapContentSize()
            .clickable(
                interactionSource = interaction,
                indication = null
            ) {
                if (numberOfRemainingClicks > 0) {
                    isNumberVisible = true
                } else {
                    finalClickCall()
                }
            },
        contentAlignment = Alignment.Center
    ) {

        content.invoke()

        AnimatedVisibility(
            visible = isNumberVisible,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Text(
                text = numberOfRemainingClicks.toString(),
                color = Color.White,
                modifier = Modifier.alpha(if (isNumberVisible) 1f else 0f),
                fontSize = animatedSize.sp
            )
        }
    }
}

@Preview
@Composable
private fun ClickableCounterBoxPreview() {
    ClickableCounterBox(
        initialClicks = 3,
        finalClickCall = { },
        content = {
            Text(text = "CLICK ME")
        }
    )
}