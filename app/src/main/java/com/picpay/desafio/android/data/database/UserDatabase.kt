package com.picpay.desafio.android.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class PicPayDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}

fun getDatabase(context: Context): PicPayDatabase {
    return Room.databaseBuilder(
        context.applicationContext,
        PicPayDatabase::class.java,
        "picpay_database"
    )
        .fallbackToDestructiveMigration()
        .build()
}


fun getDao(database: PicPayDatabase) = database.userDao()
