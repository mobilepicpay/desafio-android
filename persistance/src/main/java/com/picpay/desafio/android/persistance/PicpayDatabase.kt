package com.picpay.desafio.android.persistance

import androidx.room.Database
import androidx.room.RoomDatabase
import com.picpay.desafio.android.persistance.dao.UserDao
import com.picpay.desafio.android.persistance.models.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class PicpayDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}