package com.picpay.desafio.android.di

import com.google.gson.GsonBuilder
import com.picpay.desafio.android.datasource.remote.UserRDS
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val PICPAY_SERVICE_BASE_URL = "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/"

val remoteModule = module{
    single { retrofit().create(UserRDS::class.java) }
}

private fun okHttp() = OkHttpClient.Builder()
    .build()

private fun gson() = GsonBuilder().create()


private fun retrofit() =
    Retrofit.Builder()
        .baseUrl(PICPAY_SERVICE_BASE_URL)
        .client(okHttp())
        .addConverterFactory(GsonConverterFactory.create(gson()))
        .build()
