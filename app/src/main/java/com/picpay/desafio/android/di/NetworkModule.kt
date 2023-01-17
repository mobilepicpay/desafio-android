package com.android.post.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.picpay.desafio.android.BuildConfig
import com.picpay.desafio.android.data.source.remote.PicPayService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TIME_OUT = 30L

val NetworkModule = module {

    single { createService(get()) }

    single { createRetrofit(get(), get(), BuildConfig.BASE_URL) }

    single { OkHttpClient.Builder() }

    single { GsonBuilder().create() }
}

fun createRetrofit(okHttpClient: OkHttpClient, gson: Gson, url: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson)).build()
}

fun createService(retrofit: Retrofit): PicPayService {
    return retrofit.create(PicPayService::class.java)
}
