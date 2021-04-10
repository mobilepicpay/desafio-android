package com.picpay.desafio.android.shared.data.remote

import com.google.gson.annotations.SerializedName

data class UserRemote(
    @SerializedName("img") val img: String,
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Int,
    @SerializedName("username") val username: String
)
