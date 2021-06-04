package com.picpay.desafio.android.remote.model

import com.google.gson.annotations.SerializedName

// TODO parcealizar classe
class UserPayload(
    @SerializedName("users") val users: List<UserData>
)

class UserData(
    @SerializedName("id") val id: Int,
    @SerializedName("img") val img: String,
    @SerializedName("name") val name: String,
    @SerializedName("username") val username: String
)