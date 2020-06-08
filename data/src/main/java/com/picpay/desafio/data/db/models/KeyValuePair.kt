package com.picpay.desafio.data.db.models

import androidx.room.Entity

@Entity(primaryKeys = ["key"])
data class KeyValuePair(
    val key: String, val value: String
)