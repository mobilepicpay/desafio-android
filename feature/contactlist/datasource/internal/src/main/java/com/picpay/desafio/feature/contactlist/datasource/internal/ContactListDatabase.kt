package com.picpay.desafio.feature.contactlist.datasource.internal

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class], version = 1)
abstract class ContactListDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        fun createDatabase(context: Context): ContactListDatabase {
            return Room
                .databaseBuilder(context, ContactListDatabase::class.java, "contact-db")
                .build()
        }
    }
}