package com.picpay.desafio.android.di

import com.picpay.desafio.android.data.repository.UserRepositoryImpl
import com.picpay.desafio.android.domain.repository.UserRepository
import org.koin.dsl.module

val appModule = module {
    single<UserRepository> { UserRepositoryImpl(get(), get(), get()) }
}
