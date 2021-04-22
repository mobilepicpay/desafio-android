package com.picpay.desafio.android.persistance.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @ColumnInfo(name = "img_url") val img: String,
    @ColumnInfo(name = "name_user") val name: String,
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "user_name") val username: String
)
