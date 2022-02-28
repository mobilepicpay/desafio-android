package com.picpay.desafio.android.data.models

import com.google.gson.annotations.SerializedName
import com.picpay.desafio.android.domain.entities.UserEntity


data class UserResponse(
    @SerializedName("img") val img: String,
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Int,
    @SerializedName("username") val username: String
)

object UserMapper {
    fun mapToDomain(items: List<UserResponse>): List<UserEntity> {
        return items.map {
            UserEntity(
                img = it.img,
                name = it.name,
                id = it.id,
                username = it.username
            )
        }
    }
}