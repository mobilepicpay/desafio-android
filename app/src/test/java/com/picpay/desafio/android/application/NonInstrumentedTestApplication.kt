package com.picpay.desafio.android.application

import android.app.Application
import com.picpay.desafio.android.di.appModule
import com.picpay.desafio.android.di.getApisModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NonInstrumentedTestApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        configKoin()
    }

    private fun configKoin() {
        startKoin {
            androidContext(this@NonInstrumentedTestApplication)
            modules(appModule + getApisModule("http://localhost:8080"))
        }
    }
}