package com.picpay.desafio.feature.contactlist.repository.impl.di

import com.picpay.desafio.feature.contactlist.repository.UserRepository
import com.picpay.desafio.feature.contactlist.repository.impl.UserRepositoryImpl
import org.koin.dsl.module

val contactListRepositoryModule = module {
    factory<UserRepository> { UserRepositoryImpl(get(), get()) }
}