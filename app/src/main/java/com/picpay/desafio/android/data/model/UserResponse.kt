package com.picpay.desafio.android.data.model

import com.google.gson.annotations.SerializedName

data class UserResponse(

    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("img") val img: String,
    @SerializedName("username") val username: String

)
