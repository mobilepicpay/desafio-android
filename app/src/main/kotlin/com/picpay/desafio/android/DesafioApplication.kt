package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.di.desafioAppModule
import com.picpay.desafio.android.sync.work.initializers.Sync
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.context.startKoin

class DesafioApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@DesafioApplication)
            workManagerFactory()
            modules(desafioAppModule)
        }
        Sync.initialize(this)
    }
}