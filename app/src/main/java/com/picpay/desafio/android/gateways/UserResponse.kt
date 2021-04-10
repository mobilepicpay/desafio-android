package com.picpay.desafio.android.gateways

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("img") val img: String,
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Int,
    @SerializedName("username") val username: String
)
