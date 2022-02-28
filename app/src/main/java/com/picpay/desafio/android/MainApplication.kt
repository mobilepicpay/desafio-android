package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.core.di.AppModule
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(AppModule.mNetworkModules)
            modules(AppModule.mRepositoryModules)
            modules(AppModule.mUseCaseModules)
            modules(AppModule.mViewModels)
        }
    }
}