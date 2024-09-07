package com.picpay.desafio.android.feature.dinogame.util

import kotlin.properties.Delegates

var deviceDensity by Delegates.notNull<Float>()

object Converter {

    fun convertPixelsToDp(pixels: Int) =
        (pixels / deviceDensity).toFloat()

    fun convertDpToPixels(dp: Float) =
        (dp * deviceDensity)
}
