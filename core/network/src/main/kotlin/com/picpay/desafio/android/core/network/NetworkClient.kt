package com.picpay.desafio.android.core.network

import android.util.Log
import com.picpay.desafio.android.core.network.NetworkClient.okHttpClientBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val TIMEOUT_IN_SECONDS = 6L

object NetworkClient {

    fun okHttpClientBuilder() = OkHttpClient.Builder()
        .connectTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
        .addInterceptor(
            HttpLoggingInterceptor {
                Log.d("OKHTTP", it)
            }.setLevel(HttpLoggingInterceptor.Level.BODY)
        )
        .build()

    fun retrofitBuilder(
        baseUrl: String,
        okHttpClient: OkHttpClient = okHttpClientBuilder()
    ): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
}