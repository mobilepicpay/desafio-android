    package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PicPayApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PicPayApplication)
            modules(
                listOf(
                    databaseModule,
                    daoModule,
                    apiModule,
                    reposityModule,
                    viewModelModule
                )
            )
        }
    }
}