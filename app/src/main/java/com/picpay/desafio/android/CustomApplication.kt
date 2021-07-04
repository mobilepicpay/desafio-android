package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.data.di.dataModule
import com.picpay.desafio.android.di.mainModule
import com.picpay.desafio.android.domain.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CustomApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@CustomApplication)
            modules(domainModule + dataModule + mainModule)
        }
    }
}