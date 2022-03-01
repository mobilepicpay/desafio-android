package com.picpay.desafio.android.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class UserDb(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val username: String,
    val img: String
)