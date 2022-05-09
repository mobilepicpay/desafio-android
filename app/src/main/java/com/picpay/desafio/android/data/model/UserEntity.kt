package com.picpay.desafio.android.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.picpay.desafio.android.domain.models.User

@Entity(tableName = "user")
internal data class UserEntity(
    @ColumnInfo(name = "img") val img: String,
    @ColumnInfo(name = "name") val name: String,
    @PrimaryKey val id: Int = 0,
    @ColumnInfo(name = "username") val username: String
)

/**
 * Maps UserResponse class to User class
 * @throws
 * */
internal fun UserEntity.mapToUser() = User(
    img = img,
    name = name,
    id = id,
    username = username
)

