package com.picpay.desafio.android

import android.app.Application

open class App: Application() {

    override fun onCreate() {
        super.onCreate()
    }

    open fun getConfig(): AppConfig = AppConfig()
}