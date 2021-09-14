package com.picpay.desafio.android.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = UserEntity.TABLE_NAME)
data class UserEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val img: String,
    val name: String,
    val username: String
) {

    companion object {

        const val TABLE_NAME = "users"
    }
}
