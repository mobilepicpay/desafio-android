package com.picpay.desafio.data.db.models

import androidx.room.Entity

@Entity(primaryKeys = ["id"])
data class UserData(
    val id: Int = 0,
    val name: String= "",
    val img: String = "",
    val username: String = ""
)