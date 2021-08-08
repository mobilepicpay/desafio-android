package com.picpay.desafio.android

import android.app.Application
import com.picpay.android.user.UserDi
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application : Application() {

    private val runNetworkMockState = true

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@Application)
            modules(
                UserDi(BuildConfig.BASE_URL, isCanDoMockState()).getModule()
            )
        }
    }

    private fun isCanDoMockState() : Boolean {
        return BuildConfig.DEBUG || runNetworkMockState
    }

}