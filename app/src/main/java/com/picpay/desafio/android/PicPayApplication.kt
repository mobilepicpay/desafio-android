package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.data.database.di.databaseModule
import com.picpay.desafio.android.data.di.PicPayServiceFactory
import com.picpay.desafio.android.data.di.dataModule
import com.picpay.desafio.android.ui.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PicPayApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@PicPayApplication)
            modules(appModule, dataModule, PicPayServiceFactory().picPayServiceModule(), databaseModule)
        }
    }
}