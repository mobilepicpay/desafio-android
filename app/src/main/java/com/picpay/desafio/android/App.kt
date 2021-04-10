package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.feature.home.di.FeatureHomeKoin
import com.picpay.desafio.android.shared.di.SharedKoin
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        initLogger()
        initDependencyInjection()
    }

    private fun initLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun initDependencyInjection() {
        startKoin {
            androidContext(this@App)

            arrayListOf(SharedKoin, FeatureHomeKoin)
                .forEach { it.loadModule(this) }
        }
    }
}