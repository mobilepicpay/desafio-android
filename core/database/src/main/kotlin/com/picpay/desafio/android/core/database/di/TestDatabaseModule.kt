package com.picpay.desafio.android.core.database.di


import androidx.room.RoomDatabase
import com.picpay.desafio.android.core.database.DesafioDatabase
import com.picpay.desafio.android.core.database.fake.FakeDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val testingDatabaseModule = module {

    single { FakeDatabase.roomDatabaseBuilder(androidApplication()) }

    single { get<DesafioDatabase>().usersDao() }

}