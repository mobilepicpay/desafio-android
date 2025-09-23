package com.picpay.desafio.android.di

import com.picpay.desafio.android.di.modules.dataModule
import com.picpay.desafio.android.di.modules.domainModule
import com.picpay.desafio.android.di.modules.networkModule
import com.picpay.desafio.android.di.modules.presentationModule
import org.koin.dsl.module

val appModule = module {
    includes(networkModule, dataModule, domainModule, presentationModule)
}
