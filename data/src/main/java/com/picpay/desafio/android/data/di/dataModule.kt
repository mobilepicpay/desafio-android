package com.picpay.desafio.android.data.di

import com.picpay.desafio.android.data.remote.repository.UserRemoteRepositoryImpl
import com.picpay.desafio.android.data.remote.service.UserService
import com.picpay.desafio.android.data.remote.util.createWebService
import com.picpay.desafio.android.domain.repository.UserRepository
import org.koin.dsl.module

val dataModule = module {
    single<UserService> { createWebService() }
    single<UserRepository> { UserRemoteRepositoryImpl(get()) }
}