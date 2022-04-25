package com.picpay.desafio.android.contacts.data.model

import com.google.gson.annotations.SerializedName

data class ContactResponse(
    @SerializedName("img") val img: String,
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Int,
    @SerializedName("username") val username: String
)