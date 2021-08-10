package com.picpay.desafio.android.network

import com.picpay.desafio.android.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {
    fun <T> build(apiClass: Class<T>): T {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(apiClass)
    }
}