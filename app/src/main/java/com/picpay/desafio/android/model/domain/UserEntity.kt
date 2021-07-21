package com.picpay.desafio.android.model.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.picpay.desafio.android.model.network.User

@Entity(tableName = "user")
class UserEntity(
    @PrimaryKey
    val id: Int,
    val img: String?,
    val name: String?,
    val username: String?
){

    fun convertToUserNetwork() : User{
        return User(
            img, name, id, username
        )
    }
}