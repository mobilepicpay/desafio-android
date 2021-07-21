package com.picpay.desafio.android.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.picpay.desafio.android.data.local.entities.UserDb

@Database(entities = [UserDb::class], version = 1, exportSchema = false)
abstract class DatabasePicPay: RoomDatabase() {
    abstract fun userDao(): UserDao

companion object {
    fun createDatabase(context: Context): DatabasePicPay =
        Room.
        databaseBuilder(context, DatabasePicPay::class.java, "user.db")
            .build()
    }
}