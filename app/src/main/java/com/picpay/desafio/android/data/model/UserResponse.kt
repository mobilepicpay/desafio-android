package com.picpay.desafio.android.data.model

import com.google.gson.annotations.SerializedName

data class UserResponse(

    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("img") val imgUrl: String,
    @SerializedName("username") val userName: String

)
