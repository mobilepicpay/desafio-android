package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.data._config.DataModule
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(DataModule.module)
        }
    }
}