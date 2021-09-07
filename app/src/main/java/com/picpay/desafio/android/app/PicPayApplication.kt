package com.picpay.desafio.android.app

import android.app.Application
import com.picpay.desafio.android.data.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PicPayApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@PicPayApplication)
            modules(mainModule)
        }
        instance = this
    }
    companion object {
        lateinit var instance: PicPayApplication
    }
}