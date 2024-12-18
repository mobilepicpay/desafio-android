package com.picpay.desafio.android.data.model

import android.graphics.Bitmap

data class UserUiData(
    val id: Int,
    val username: String,
    val name: String,
    val imgBitmap: Bitmap?
)
