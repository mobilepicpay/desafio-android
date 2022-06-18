package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.di.cacheModule
import com.picpay.desafio.android.di.remoteModule
import com.picpay.desafio.android.di.repositoryModule
import com.picpay.desafio.android.di.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class PicPayApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@PicPayApplication)
            modules(
                cacheModule,
                remoteModule,
                repositoryModule,
                useCaseModule,
            )
        }
    }
}