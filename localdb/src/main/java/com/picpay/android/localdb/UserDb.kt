package com.picpay.android.localdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserDb(
    @PrimaryKey val id: Long = 0,
    val img: String,
    val name: String,
    val username: String
)