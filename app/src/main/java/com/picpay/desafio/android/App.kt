package com.picpay.desafio.android

import android.app.Application
import android.os.Debug
import com.picpay.desafio.android.commons.base.baseModule
import com.picpay.desafio.android.contacts.di.contactsModule
import com.picpay.desafio.android.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(
                listOf(
                    appModule,
                    baseModule,
                    contactsModule
                )
            )
        }
    }
}