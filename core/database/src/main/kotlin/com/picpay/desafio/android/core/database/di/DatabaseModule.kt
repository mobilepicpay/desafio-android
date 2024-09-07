package com.picpay.desafio.android.core.database.di

import com.picpay.desafio.android.core.database.DesafioDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    single { DesafioDatabase.roomDatabaseBuilder(androidApplication()) }

    single { get<DesafioDatabase>().usersDao() }

}