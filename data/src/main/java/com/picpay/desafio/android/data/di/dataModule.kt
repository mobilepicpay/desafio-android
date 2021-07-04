package com.picpay.desafio.android.data.di

import com.picpay.desafio.android.data.local.database.UserDataBase
import com.picpay.desafio.android.data.local.datasource.UserLocalDataSource
import com.picpay.desafio.android.data.local.datasource.UserLocalDataSourceImpl
import com.picpay.desafio.android.data.remote.datasource.UserRemoteDataSource
import com.picpay.desafio.android.data.remote.datasource.UserRemoteDataSourceImpl
import com.picpay.desafio.android.data.remote.service.UserService
import com.picpay.desafio.android.data.remote.util.createWebService
import com.picpay.desafio.android.data.repository.UserRepositoryImpl
import com.picpay.desafio.android.domain.repository.UserRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    single<UserService> { createWebService() }
    single<UserRepository> { UserRepositoryImpl(get(), get()) }
    factory<UserLocalDataSource> { UserLocalDataSourceImpl(get()) }
    factory<UserRemoteDataSource> { UserRemoteDataSourceImpl(get()) }
    single { UserDataBase.createDataBase(androidContext()) }
}