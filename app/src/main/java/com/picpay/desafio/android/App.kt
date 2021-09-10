package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.di.dataModule
import com.picpay.desafio.android.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                listOf(
                    viewModelModule,
                    dataModule,
                )
            ).androidContext(this@App)
        }
    }
}