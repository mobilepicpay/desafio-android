package com.picpay.desafio.common.di

import com.google.gson.GsonBuilder
import com.picpay.desafio.common.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {

    val modules = module {

        single { provideOkHttpClient() }

        single { provideRetrofit(get()) }
    }

    private fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .followRedirects(true)
            .addNetworkInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        return builder.build()
    }


    private fun provideRetrofit(
        client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }
}