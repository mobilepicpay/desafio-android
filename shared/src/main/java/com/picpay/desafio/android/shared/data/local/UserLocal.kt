package com.picpay.desafio.android.shared.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserLocal(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "user_name") val username: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "img_url") val imageUrl: String
)