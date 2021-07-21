package com.picpay.desafio.android.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "USER")
data class UserDb(

    @PrimaryKey val id: Int?,
    val name: String?,
    val username: String?,
    val img: String?
)