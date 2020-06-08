package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.data.di.DataModule
import com.picpay.desafio.android.di.PresentationModule
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

        setupKoin()
    }

    private fun setupKoin() {
        val modules = PresentationModule.getModules()
        modules.addAll(DataModule.getModules())

        startKoin {
            androidLogger()
            androidContext(this@PicPayApplication)
            modules(modules)
        }
    }
}