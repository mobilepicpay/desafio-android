package com.desafio.picpay.android.testcoreutil

import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MockServer {
    const val PORT = 8080
    private const val URL = "http://127.0.0.1:$PORT"

    private fun setupRetrofit(client: OkHttpClient) = Retrofit.Builder()
        .client(client)
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val module = module(override = true) {
        factory<Retrofit> { setupRetrofit(get()) }
        factory { OkHttpClient.Builder().build() }
    }

}