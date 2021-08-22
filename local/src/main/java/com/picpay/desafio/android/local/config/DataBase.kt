package com.picpay.desafio.android.local.config

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.picpay.desafio.android.local.dao.UserDAO
import com.picpay.desafio.android.model.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class DataBase : RoomDatabase() {

    abstract fun userDAO(): UserDAO

    companion object {
        @Volatile
        private var INSTANCE: DataBase? = null
        fun invoke(context: Context) : DataBase {

            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance =  Room.databaseBuilder(context.applicationContext,
                    DataBase::class.java,
                    "picpay")
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}