package com.picpay.desafio.android.di

import com.picpay.desafio.android.usecases.GetUsersUseCases
import com.picpay.desafio.android.usecases.GetUsersUseCasesImpl
import org.koin.dsl.module

val useCaseModule = module {
    factory<GetUsersUseCases> {
        GetUsersUseCasesImpl(
            repository = get()
        )
    }
}

val domainModule = listOf(useCaseModule)