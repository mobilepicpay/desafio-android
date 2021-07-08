package com.picpay.desafio.android.di

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    val gson = GsonBuilder().create()

    val gsonFactory = GsonConverterFactory.create(gson)

    single {
        OkHttpClient.Builder()
            .build()
    }

    single<Retrofit> {
        buildRetrofit(get(), gsonFactory, "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/")
    }
}

private fun buildRetrofit(client: OkHttpClient, gsonFactory: GsonConverterFactory, url: String) =
    Retrofit.Builder()
        .client(client)
        .baseUrl(url)
        .addConverterFactory(gsonFactory)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()