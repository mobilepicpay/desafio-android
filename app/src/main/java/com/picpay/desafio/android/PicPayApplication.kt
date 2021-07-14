package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.di.ApplicationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * @author Vitor Herrmann on 12/07/2021
 */
class PicPayApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@PicPayApplication)
            modules(ApplicationModule.modules)
        }
    }
}
