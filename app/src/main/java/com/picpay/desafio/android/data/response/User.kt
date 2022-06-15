package com.picpay.desafio.android.data.response

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("img")
    val img: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("username")
    val username: String
)