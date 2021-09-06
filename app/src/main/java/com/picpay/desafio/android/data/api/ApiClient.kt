package com.picpay.desafio.android.data.api

import com.picpay.desafio.android.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private val okHttp: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .build()
    }
    var retrofit: Retrofit = Retrofit.Builder()
        .client(okHttp)
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}