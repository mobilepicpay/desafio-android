package com.picpay.desafio.android.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.picpay.desafio.android.room.dao.StringKeyValueDao
import com.picpay.desafio.android.room.dao.UserDao
import com.picpay.desafio.android.room.models.StringKeyValuePair
import com.picpay.desafio.android.room.models.User

@Database(
    entities = [User::class, StringKeyValuePair::class], version = 1
)

abstract class PicPayDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun keyValueDao(): StringKeyValueDao
}