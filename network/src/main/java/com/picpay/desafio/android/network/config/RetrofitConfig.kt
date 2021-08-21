package com.picpay.desafio.android.network.config

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfig(
    private val baseUrl: String,
    private val gsonConverterFactory: GsonConverterFactory,
    private val okHttpClient: OkHttpClient
) {

    fun <T>create(service: Class<T>): T {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build().create(service)
    }
}