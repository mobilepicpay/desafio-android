package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.modules.RetrofitModule
import com.picpay.desafio.android.modules.repositoryModule
import com.picpay.desafio.android.modules.uiModule
import com.picpay.desafio.android.modules.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoinConfig()
    }

    private fun startKoinConfig(){
        startKoin {
            androidContext(this@AppApplication)
            RetrofitModule.load()
            modules(
                listOf(
                    viewModelModule,
                    repositoryModule,
                    uiModule
                )
            )
        }
    }
}