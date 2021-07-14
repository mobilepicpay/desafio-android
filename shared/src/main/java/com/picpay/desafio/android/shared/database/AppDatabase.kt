package com.picpay.desafio.android.shared.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.picpay.desafio.android.shared.model.ViewUser

/**
 * @author Vitor Herrmann on 13/07/2021
 */
@Database(entities = [ViewUser::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun contactsDao(): ContactsDao
}
