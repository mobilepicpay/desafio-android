package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.persistance.di.localDataSourceModule
import com.picpay.desafio.android.rest_picpay.di.restPicpayModule
import com.picpay.desafio.android.feature_contacts.di.featureContactModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PicPayApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@PicPayApplication)
            modules(
                listOf(
                    featureContactModule,
                    localDataSourceModule,
                    restPicpayModule
                )
            )
        }
    }
}