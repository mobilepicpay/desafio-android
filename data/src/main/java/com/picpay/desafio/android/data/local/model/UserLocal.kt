package com.picpay.desafio.android.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = TABLE_NAME)
data class UserLocal(
    @PrimaryKey(autoGenerate = true) var id: Int?,
    @ColumnInfo(name = COLUMN_IMG) val img: String?,
    @ColumnInfo(name = COLUMN_NAME) val name: String?,
    @ColumnInfo(name = COLUMN_USERNAME) val username: String?
)

const val TABLE_NAME = "user"
const val COLUMN_NAME = "name"
const val COLUMN_USERNAME = "username"
const val COLUMN_IMG = "img"