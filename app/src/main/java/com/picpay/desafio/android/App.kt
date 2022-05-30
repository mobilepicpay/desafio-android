package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.common._config.CommonModule
import com.picpay.desafio.android.data._config.DataModule
import com.picpay.desafio.android.presenter._config.PresenterModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                CommonModule.module,
                DataModule.module,
                PresenterModule.module,
            )
        }
    }
}