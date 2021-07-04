package com.picpay.desafio.android.domain.di

import com.picpay.desafio.android.domain.usecase.UserUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { UserUseCase(get()) }
}