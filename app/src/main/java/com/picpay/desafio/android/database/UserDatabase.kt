package com.picpay.desafio.android.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.picpay.desafio.android.database.dao.UserDao
import com.picpay.desafio.android.repository.model.UserLocal

@Database(entities = [UserLocal::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase()  {

    abstract fun userDao() : UserDao



}