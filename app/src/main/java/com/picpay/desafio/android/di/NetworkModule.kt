package com.picpay.desafio.android.di

import com.google.gson.GsonBuilder
import com.picpay.desafio.android.R
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author Vitor Herrmann on 12/07/2021
 */
object NetworkModule {

    private const val TIMEOUT = 60L

    val module = module {

        single {
            GsonBuilder()
                .setLenient()
                .create()
        }

        single {
            OkHttpClient.Builder()
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(
                    HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
                )
                .build().let { client ->
                    Retrofit.Builder()
                        .baseUrl(androidContext().getString(R.string.api_url))
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create(get()))
                        .build()
                }
        }
    }
}
