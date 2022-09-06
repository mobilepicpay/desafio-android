package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.di.MainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PicPayApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@PicPayApp)
        }
        MainModule.load()
    }
}