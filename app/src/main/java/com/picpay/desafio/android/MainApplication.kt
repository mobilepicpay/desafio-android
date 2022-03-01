package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.core.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(AppModule.networkModules)
            modules(AppModule.dbModule)
            modules(AppModule.repositoryModules)
            modules(AppModule.useCaseModules)
            modules(AppModule.viewModels)
        }
    }
}