package com.picpay.desafio.android.data.dto

import com.google.gson.annotations.SerializedName
import com.picpay.desafio.android.data.mapper.UserMapper

data class UserResponse(
    @SerializedName("img") val img: String,
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Int,
    @SerializedName("username") val username: String
)

fun List<UserResponse>?.toUsersDb() = UserMapper.toUsersDb(this.orEmpty())