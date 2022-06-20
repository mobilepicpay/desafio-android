package com.picpay.desafio.network

import okhttp3.CertificatePinner
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object NetworkClientProvider {

    object NetworkConstants {
        val TIME_UNIT = TimeUnit.MINUTES
        val READ_TIMEOUT = 5L
        val CONNECTION_TIMEOUT = 5L
        val BASE_URL = "https://brasileirao-app.herokuapp.com"
    }

    fun buildOkHttpClient(
        pinner: CertificatePinner? = null,
        interceptor: Interceptor? = null
    ): OkHttpClient {
        val client = OkHttpClient.Builder()
            .readTimeout(NetworkConstants.READ_TIMEOUT, NetworkConstants.TIME_UNIT)
            .connectTimeout(NetworkConstants.CONNECTION_TIMEOUT, NetworkConstants.TIME_UNIT)

        client.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))

        pinner?.let { client.certificatePinner(it) }
        interceptor?.let { client.addInterceptor(it) }

        return client.build()
    }

    fun buildRetrofitClient(okHttpClient: OkHttpClient, url: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
}