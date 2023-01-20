package com.picpay.desafio.android.application

import android.app.Application
import com.picpay.desafio.android.di.appModule
import com.picpay.desafio.android.di.databaseModule
import com.picpay.desafio.android.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@AppApplication)
            modules(appModule, networkModule, databaseModule)
        }
    }
}
