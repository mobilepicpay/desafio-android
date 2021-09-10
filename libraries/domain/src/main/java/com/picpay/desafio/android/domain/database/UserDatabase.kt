package com.picpay.desafio.android.domain.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class], version = 1)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDataAccess(): UserDao

    companion object {

        private const val userDatabaseName = "user"

        fun loadDatabase(context: Context): UserDao {
            return Room.databaseBuilder(
                context,
                UserDatabase::class.java,
                userDatabaseName
            ).build()
                .userDataAccess()
        }

    }

}