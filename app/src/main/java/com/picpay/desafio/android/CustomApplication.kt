package com.picpay.desafio.android

import android.app.Application
import org.koin.core.context.startKoin

class CustomApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(KoinModules.all)
        }
    }
}
