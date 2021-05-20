package com.picpay.desafio.android.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.picpay.desafio.android.data.model.DbUser


@Database(entities = [DbUser::class], version = 1, exportSchema = false)

abstract class DataBaseProvider : RoomDatabase(){

    abstract fun provideUserDao(): UserDao
}