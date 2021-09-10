package com.picpay.desafio.android.data.utils

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WebServiceFactory {

    inline fun <reified T> createWebService(
        okHttpClient: OkHttpClient,
        url: String
    ): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(GsonUtil.gson))
            .build()
        return retrofit.create(T::class.java)
    }



}