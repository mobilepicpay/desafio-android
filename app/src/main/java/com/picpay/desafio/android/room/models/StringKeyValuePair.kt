package com.picpay.desafio.android.room.models

import androidx.room.Entity

@Entity(primaryKeys = ["key"])
data class StringKeyValuePair(
    val key: String, val value: String
)