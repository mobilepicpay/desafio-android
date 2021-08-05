package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.common.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PicPayApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@PicPayApplication)

            androidLogger()

            modules(appModules)
        }
    }
}