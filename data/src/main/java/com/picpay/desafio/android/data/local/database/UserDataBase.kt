package com.picpay.desafio.android.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.picpay.desafio.android.data.local.dao.UserDao
import com.picpay.desafio.android.data.local.model.UserLocal

@Database(version = 1, entities = [UserLocal::class])
abstract class UserDataBase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        fun createDataBase(context: Context) : UserDao {
            return Room
                .databaseBuilder(context, UserDataBase::class.java, "Users.db")
                .build()
                .userDao()
        }
    }
}