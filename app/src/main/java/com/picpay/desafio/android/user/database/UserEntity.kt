package com.picpay.desafio.android.user.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.picpay.desafio.android.user.domain.UserDomain

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "username")
    var userName: String,

    @ColumnInfo(name = "image_url")
    var imageUrl: String
)

fun List<UserEntity>.toDomain(): List<UserDomain> {
    return map {
        UserDomain(
            id = it.id,
            name = it.name,
            userName = it.userName,
            imageUrl = it.imageUrl
        )
    }
}