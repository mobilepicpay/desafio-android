package com.picpay.desafio.android.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.picpay.desafio.android.data.model.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): UserDao
}
