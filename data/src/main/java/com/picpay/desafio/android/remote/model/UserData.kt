package com.picpay.desafio.android.remote.model

import com.google.gson.annotations.SerializedName

class UserData(
    @SerializedName("id") val id: Int,
    @SerializedName("img") val img: String,
    @SerializedName("name") val name: String,
    @SerializedName("username") val username: String
)