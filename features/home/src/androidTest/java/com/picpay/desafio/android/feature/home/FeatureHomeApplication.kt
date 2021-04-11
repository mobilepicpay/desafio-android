package com.picpay.desafio.android.feature.home

import android.app.Application
import com.picpay.desafio.android.feature.home.di.FeatureHomeKoin
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class FeatureHomeApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initDependencyInjection()
    }

    private fun initDependencyInjection() {
        startKoin {
            androidContext(this@FeatureHomeApplication)

            arrayListOf(SharedMockKoin, FeatureHomeKoin)
                .forEach { it.loadModule(this) }
        }
    }
}