package com.picpay.desafio.android.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.picpay.desafio.android.data.UserEntity

@Database(entities = [UserEntity::class], version = PicPayDatabase.VERSION)
abstract class PicPayDatabase : RoomDatabase() {

    abstract fun userDao(): UserDAO

    companion object {

        const val VERSION = 1
        const val NAME = "pic-pay-database-cache"
    }
}
