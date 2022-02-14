package com.picpay.desafio.userlist.utils.rules

import android.app.Application
import com.picpay.desafio.common.di.NetworkModule
import com.picpay.desafio.userlist.data.service.PicPayService
import com.picpay.desafio.userlist.data.service.PicPayServiceMock
import com.picpay.desafio.userlist.di.UserListModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

class AppAndroidTest : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@AppAndroidTest)
            modules(listOf(
                UserListModule.modules,
                NetworkModule.modules
            ))
            module {
                single(override = true) { PicPayServiceMock() as PicPayService }
            }
        }
    }
}