package com.picpay.desafio.android.di

import com.picpay.desafio.android.BuildConfig
import com.picpay.desafio.android.data.api.UserService
import com.picpay.desafio.android.data.utils.WebServiceFactory
import com.picpay.desafio.android.domain.database.UserDatabase
import com.picpay.desafio.android.domain.repository.UserServiceRepository
import com.picpay.desafio.android.domain.repository.UserServiceRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    factory {
        WebServiceFactory.createWebService<UserService>(BuildConfig.BASE_URL_API)
    }

    factory<UserServiceRepository> { UserServiceRepositoryImpl(get(), get()) }

    single { UserDatabase.loadDatabase(androidContext()) }
}
