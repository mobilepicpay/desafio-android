package com.picpay.desafio.android.di

import com.picpay.desafio.android.datasource.repository.UserRepository
import com.picpay.desafio.android.domain.repository.UserDataRepository
import io.reactivex.schedulers.Schedulers.single
import org.koin.dsl.module

val repositoryModule = module {
    single<UserDataRepository> { UserRepository(get(), get()) }
}