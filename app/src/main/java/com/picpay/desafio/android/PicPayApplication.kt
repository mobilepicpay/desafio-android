package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.di.datasource.dataSourceModule
import com.picpay.desafio.android.di.network.networkModule
import com.picpay.desafio.android.di.repository.repositoryModule
import com.picpay.desafio.android.di.viewmodel.viewModelModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

@ExperimentalCoroutinesApi
class PicPayApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@PicPayApplication)
            koin.loadModules(
                listOf(
                    networkModule,
                    dataSourceModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }
}