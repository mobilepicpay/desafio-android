package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.di.*
import com.picpay.desafio.android.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class Application :  Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@Application)
            modules(
                networkModule,
                serviceModule,
                dataBase,
                repositoryModule,
                viewModelModule
            )
        }
    }
}