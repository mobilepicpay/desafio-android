package com.picpay.desafio.android.common.di

import com.picpay.desafio.android.BuildConfig
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    factory { OkHttpClient().newBuilder().build() }

    single {
        Retrofit.Builder().baseUrl(BuildConfig.API_URL).client(get())
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
}