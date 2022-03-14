package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.di.URL_BASE
import com.picpay.desafio.android.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

open class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AppApplication)
            androidLogger()
            modules(appModules)
            properties(mapOf(URL_BASE to "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/"))

        }
    }
}