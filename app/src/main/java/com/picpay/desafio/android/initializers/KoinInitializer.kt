package com.picpay.desafio.android.initializers

import android.app.Application
import com.picpay.desafio.android.di.networkModule
import com.picpay.desafio.android.di.userModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

object KoinInitializer {
    val modules = listOf(
        networkModule,
        userModule
    )

    fun initialize(application: Application) {
        startKoin {
            androidContext(application)
            modules(modules)
        }
    }
}