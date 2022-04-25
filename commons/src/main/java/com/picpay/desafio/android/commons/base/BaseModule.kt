package com.picpay.desafio.android.commons.base

import org.koin.dsl.module

val baseModule = module {
    single<SchedulerProvider> {
        SchedulerProviderImpl()
    }
}