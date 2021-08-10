package com.picpay.desafio.android.user.network

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.picpay.desafio.android.user.domain.UserDomain
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserResponse(
    @SerializedName("img") val img: String,
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Int,
    @SerializedName("username") val username: String
) : Parcelable

fun List<UserResponse>.toDomain(): List<UserDomain> {
    return map {
        UserDomain(
            id = it.id,
            name = it.name,
            userName = it.username,
            imageUrl = it.img
        )
    }
}