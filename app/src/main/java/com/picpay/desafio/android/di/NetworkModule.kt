package com.picpay.desafio.android.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.picpay.desafio.android.BuildConfig
import com.picpay.desafio.android.data.source.remote.PicPayService
import com.picpay.desafio.android.data.source.remote.UserRemoteDataSource
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val TIME_OUT = 30L

val networkModule = module {

    single { createService(get()) }

    single { createRetrofit(get(), get(), BuildConfig.BASE_URL) }

    single { createOkHttpClient() }

    single { GsonBuilder().create() }

    single { UserRemoteDataSource(get()) }
}

fun createOkHttpClient(): OkHttpClient =
    OkHttpClient.Builder()
        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(TIME_OUT, TimeUnit.SECONDS)
        .build()

fun createRetrofit(okHttpClient: OkHttpClient, gson: Gson, url: String): Retrofit =
    Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson)).build()

fun createService(retrofit: Retrofit): PicPayService =
    retrofit.create(PicPayService::class.java)
