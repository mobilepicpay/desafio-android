package com.picpay.desafio.android.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.picpay.desafio.android.model.entity.UserEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserDTO(
    @SerializedName("img") val img: String,
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Int,
    @SerializedName("username") val username: String
) : Parcelable

fun UserDTO.toUserEntity() : UserEntity {
    return with(this) {
        UserEntity(
            img = this.img,
            name = this.name,
            id = this.id,
            username = this.username
        )
    }
}