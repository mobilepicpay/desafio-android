package com.picpay.desafio.android.rest_picpay.models

import com.google.gson.annotations.SerializedName

data class RawUserResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("img") val img: String,
    @SerializedName("name") val name: String,
    @SerializedName("username") val username: String
)