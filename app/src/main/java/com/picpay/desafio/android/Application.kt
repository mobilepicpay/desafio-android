package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.di.networkModule
import com.picpay.desafio.android.di.repositoryModule
import com.picpay.desafio.android.di.viewModelModule
import com.picpay.desafio.android.network.NetworkDI
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
                networkModule, repositoryModule, viewModelModule
            )
        }
    }
}