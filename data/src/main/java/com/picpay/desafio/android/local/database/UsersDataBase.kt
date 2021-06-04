package com.picpay.desafio.android.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.picpay.desafio.android.local.modal.UsersDataCache

@Database(version = 3, entities = [UsersDataCache::class], exportSchema = false)
abstract class UsersDataBase : RoomDatabase() {
    abstract fun usersDao(): UsersDao

    companion object {
        fun createDataBase(context: Context): UsersDao {
            return Room
                .databaseBuilder(context, UsersDataBase::class.java, "picpay.db")
                .build()
                .usersDao()
        }
    }
}