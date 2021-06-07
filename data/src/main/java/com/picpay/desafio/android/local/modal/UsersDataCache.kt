package com.picpay.desafio.android.local.modal

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UsersDataCache(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val img: String,
    val name: String,
    val username: String
)