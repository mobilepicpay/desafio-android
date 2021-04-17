package com.picpay.desafio.android.networking

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkBuilder {

    inline operator fun <reified T> invoke(okHttpClient: OkHttpClient, url: String): T {
        return with(Retrofit.Builder()) {
            addConverterFactory(GsonConverterFactory.create())
            baseUrl(url)
            client(okHttpClient)
            build().create(T::class.java)
        }
    }
}