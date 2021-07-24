package com.picpay.desafio.android.data.source.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.picpay.desafio.android.data.mapper.UserMapper

@Entity(tableName = "users")
data class UserDb(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "img") val img: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "username") val username: String
)

fun List<UserDb>?.toUsersModel() = UserMapper.toUsersModel(this.orEmpty())
