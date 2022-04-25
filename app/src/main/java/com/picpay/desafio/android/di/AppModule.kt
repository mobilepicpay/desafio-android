package com.picpay.desafio.android.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL_KEY = "BASE_URL_KEY"

val appModule = module {
    single(named(BASE_URL_KEY)) {
        "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/"
    }
    single<Gson> {
        GsonBuilder().create()
    }
    single {
        OkHttpClient
            .Builder()
            .build()
    }
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(get<String>(named(BASE_URL_KEY)))
            .client(get())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(get()))
            .build()
    }
}