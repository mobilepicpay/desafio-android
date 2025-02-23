package com.picpay.desafio.android.data.di

import android.util.Log
import com.google.gson.GsonBuilder
import com.picpay.desafio.android.data.utils.Constants.OK_HTTP
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object DataModule {
    fun load() = loadKoinModules(
        listOf(
            RepositoryModule.getModule(),
            ServiceModule.getModule(),
            DaoModule.getModule(),
            networkModule,
        )
    )

    private val networkModule = module {
        single { createOkHttpClient() }

        single { GsonBuilder().create() }
    }

    private fun createOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor {
            Log.i(OK_HTTP, it)
        }
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }
}