package com.picpay.desafio.android

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoinConfig()
    }

    private fun startKoinConfig(){
        startKoin {
            androidContext(this@AppApplication)
            modules(
                listOf(
                    viewModelModule,
                    repositoryModule,
                    uiModule
                )
            )
        }
    }
}