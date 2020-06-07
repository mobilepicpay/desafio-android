package com.picpay.desafio.android.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.picpay.desafio.android.room.dao.KeyValueDao
import com.picpay.desafio.android.room.dao.UserDao
import com.picpay.desafio.android.room.models.KeyValuePair
import com.picpay.desafio.android.room.models.User

@Database(
    entities = [User::class, KeyValuePair::class], version = 1
)

abstract class PicPayDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun keyValueDao(): KeyValueDao
}