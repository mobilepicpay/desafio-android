package com.picpay.desafio.android.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.picpay.desafio.android.user.database.UserDao
import com.picpay.desafio.android.user.database.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}