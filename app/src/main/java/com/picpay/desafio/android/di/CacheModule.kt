package com.picpay.desafio.android.di

import com.picpay.desafio.android.datasource.cache.UserDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

val cacheModule = module{
    single { UserDatabase.getInstance(get()).userDAO }
}