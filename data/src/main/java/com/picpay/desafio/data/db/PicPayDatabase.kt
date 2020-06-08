package com.picpay.desafio.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.picpay.desafio.data.db.dao.KeyValueDao
import com.picpay.desafio.data.db.dao.UserDao
import com.picpay.desafio.data.db.models.KeyValuePair
import com.picpay.desafio.data.db.models.UserData

@Database(
    entities = [UserData::class, KeyValuePair::class], version = 1
)

abstract class PicPayDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun keyValueDao(): KeyValueDao
}