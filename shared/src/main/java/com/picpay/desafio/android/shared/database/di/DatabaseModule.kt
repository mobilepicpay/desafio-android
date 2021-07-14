package com.picpay.desafio.android.shared.database.di

import androidx.room.Room
import com.picpay.desafio.android.shared.database.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * @author Vitor Herrmann on 13/07/2021
 */
object DatabaseModule {

    val module = module {
        single {
            Room.databaseBuilder(
                androidContext(),
                AppDatabase::class.java, "picpay-database"
            ).allowMainThreadQueries().build()
        }

        single { get<AppDatabase>().contactsDao() }
    }
}
