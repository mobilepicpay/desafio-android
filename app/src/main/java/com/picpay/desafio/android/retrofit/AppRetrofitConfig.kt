package com.picpay.desafio.android.retrofit

import com.picpay.desafio.android.retrofit.service.PicPayService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val URL = "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/"

class AppRetrofitConfig {
    private val okHttp: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(URL)
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val service: PicPayService by lazy {
        retrofit.create(PicPayService::class.java)
    }

}