package com.picpay.desafio.android.di

import com.picpay.desafio.android.domain.usecase.GetUsersUC
import org.koin.dsl.module

val useCaseModule = module {
    single { GetUsersUC(get()) }
}