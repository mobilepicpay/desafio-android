package com.picpay.desafio.android.data.features.contacts.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.picpay.desafio.android.data.features.contacts.local.dao.ContactDao
import com.picpay.desafio.android.data.features.contacts.local.entities.database.ContactDb

@Database(
    entities = [ContactDb::class],
    version = 1,
    exportSchema = false
)

abstract class ContactDatabase : RoomDatabase() {
    abstract val dao: ContactDao

    companion object {
        @Volatile
        private var INSTANCE: ContactDatabase? = null

        fun getInstance(context: Context): ContactDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ContactDatabase::class.java,
                        "contact_cache_db"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}