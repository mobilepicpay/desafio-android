package com.picpay.desafio.android.core

import android.app.Application
import com.picpay.desafio.common.di.NetworkModule
import com.picpay.desafio.userlist.di.UserListModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(
                listOf(
                    UserListModule.modules,
                    NetworkModule.modules
                )
            )
        }
    }
}