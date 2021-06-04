package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.di.dataModules
import com.picpay.desafio.android.di.domainModule
import com.picpay.desafio.android.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyMainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyMainApplication)

            modules(domainModule + dataModules + listOf(presentationModule))
        }
    }
}