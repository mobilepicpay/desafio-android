package com.picpay.desafio.android.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "USERS")
data class DbUser(

    @PrimaryKey
    val id: Int,
    val name: String,
    val img: String,
    val username: String
)
