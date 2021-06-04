package com.picpay.desafio.android.di

import com.picpay.desafio.android.usecases.GetUsersUseCases
import com.picpay.desafio.android.usecases.GetUsersUseCasesImpl
import org.koin.dsl.module

//    private val service: PicPayService by lazy {
//        retrofit.create(PicPayService::class.java)
//    }
val useCaseModule = module {
    factory<GetUsersUseCases> {
        GetUsersUseCasesImpl(
            repository = get()
        )
    }
}

val domainModule = listOf(useCaseModule)