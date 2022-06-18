package com.picpay.desafio.android.datasource.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey

const val USER_TABLE = "user_table"
@Entity(tableName = USER_TABLE)
data class UserCM(
    @PrimaryKey
    val name: String,
    val img: String,
    val id: Int,
    val username: String
    )