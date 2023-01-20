package com.picpay.desafio.android.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.picpay.desafio.android.data.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
