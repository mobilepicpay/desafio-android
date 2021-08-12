package com.picpay.desafio.android.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.picpay.desafio.android.data.db.PicPayDatabase.Companion.DATABASE_VERSION
import com.picpay.desafio.android.data.db.dao.UserDao
import com.picpay.desafio.android.data.db.entities.UserEntity

@Database(
    entities = [UserEntity::class],
    version = DATABASE_VERSION
)
abstract class PicPayDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        const val DATABASE_VERSION = 1
    }
}
