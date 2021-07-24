package com.picpay.desafio.android.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserDb::class], version = 1)
abstract class UsersDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}