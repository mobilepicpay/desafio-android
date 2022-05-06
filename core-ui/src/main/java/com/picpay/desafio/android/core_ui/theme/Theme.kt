package com.picpay.desafio.android.core_ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


private val LightColorPalette = lightColors(
    primary = PicpayGreen,
    primaryVariant = LightOrange,
    onPrimary = Color.White,
    secondary = Blue,
    secondaryVariant = LightBlue,
    onSecondary = Color.White,
    onBackground = AppBlack,
)

@Composable
fun PicpayTheme(content: @Composable () -> Unit) {
    val colors = LightColorPalette

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}