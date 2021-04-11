package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.di.AppTestKoin
import com.picpay.desafio.android.feature.home.di.FeatureHomeKoin
import com.picpay.desafio.android.shared.di.SharedKoin
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TestApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initDependencyInjection()
    }

    private fun initDependencyInjection() {
        startKoin {
            androidContext(this@TestApplication)

            arrayListOf(SharedKoin, FeatureHomeKoin, AppTestKoin)
                .forEach { it.loadModule(this) }
        }
    }
}