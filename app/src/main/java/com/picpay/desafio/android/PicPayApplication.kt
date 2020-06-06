package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.di.KoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class PicPayApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@PicPayApplication)
            modules(KoinModule.getModules())
        }
    }
}