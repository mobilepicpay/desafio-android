package com.picpay.desafio.android.data.database.di

import com.picpay.desafio.android.data.database.OfflineDataRepository
import com.picpay.desafio.android.data.database.UserDatabaseRepository
import com.picpay.desafio.android.data.database.getDao
import com.picpay.desafio.android.data.database.getDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single { getDatabase(androidContext()) }
    single { getDao(get()) }
    single<UserDatabaseRepository> { OfflineDataRepository(get()) }
}