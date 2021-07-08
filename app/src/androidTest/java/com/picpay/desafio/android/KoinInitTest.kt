
package com.picpay.desafio.android

import android.app.Application
import com.desafio.picpay.android.testcoreutil.MockServer
import com.picpay.desafio.android.di.userModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

object KoinInitTest {
    private val modules = listOf(
        MockServer.module,
        userModule
    )
    fun initialize(application: Application) {
        startKoin {
            androidContext(application)
            modules(modules)
        }
    }
}
