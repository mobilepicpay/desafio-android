package com.picpay.desafio.android.data.api

import com.google.gson.annotations.SerializedName

data class UserDTO(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("img") val img: String? = null,
    @SerializedName("username") val username: String? = null
)
