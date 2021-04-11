package com.picpay.desafio.android.shared.data.remote

import com.google.gson.annotations.SerializedName

data class UserRemote(
    @SerializedName("id") val id: Int,
    @SerializedName("username") val username: String,
    @SerializedName("name") val name: String,
    @SerializedName("img") val img: String
)
