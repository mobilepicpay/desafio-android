package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PicPayApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        configKoin()
    }

    private fun configKoin() {
        startKoin {
            androidContext(this@PicPayApplication)
            modules(appModule)
        }
    }
}