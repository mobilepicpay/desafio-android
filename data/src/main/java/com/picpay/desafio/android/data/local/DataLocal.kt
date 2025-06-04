package com.picpay.desafio.android.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.picpay.desafio.android.domain.model.User

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val username: String,
    val img: String
)

fun UserEntity.toUser() = User(id, name, img, username)
fun User.toEntity() = UserEntity(id, name, username, img)
