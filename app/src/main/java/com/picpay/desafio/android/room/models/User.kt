package com.picpay.desafio.android.room.models

import androidx.room.Entity

@Entity(primaryKeys = ["id"])
data class User(
    val id: Int = 0,
    val name: String= "",
    val img: String = "",
    val username: String = ""
)