package com.picpay.desafio.android.user

import com.picpay.desafio.android.AppApplication
import com.picpay.desafio.android.di.URL_BASE
import com.picpay.desafio.android.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class UserTestApp: AppApplication() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@UserTestApp)
            androidLogger()
            modules(appModules)
            properties(mapOf(URL_BASE to "http://localhost:8080"))

        }
    }

    var url = "http://localhost:8080"

    fun  getBaseUrl () : String {
        return url
    }
}