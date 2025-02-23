package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.data.di.DataModule
import com.picpay.desafio.android.domain.di.DomainModule
import com.picpay.desafio.android.presentation.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin { androidContext(this@App) }

        DomainModule.load()
        DataModule.load()
        PresentationModule.load()
    }
}