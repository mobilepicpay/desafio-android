package com.picpay.desafio.android.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    val img: String,
    val name: String,
    val id: Int,
    val username: String
)