package com.picpay.desafio.android.core.data.image.util

import android.graphics.Bitmap
import java.io.ByteArrayOutputStream

fun Bitmap.toByteArray(quality: Int = 90): ByteArray =
        ByteArrayOutputStream().let {
            this.compress(Bitmap.CompressFormat.JPEG, quality, it)
            it.toByteArray()
        }