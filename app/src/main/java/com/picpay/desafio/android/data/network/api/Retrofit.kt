package com.picpay.desafio.android.data.network.api

import com.picpay.desafio.android.BuildConfig
import com.picpay.desafio.android.data.network.adapter.FlowCallAdapterFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@ExperimentalCoroutinesApi
class Retrofit {
    companion object {
        operator fun invoke(url: String): Retrofit {
            val logging = HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            }

            val okHttp: OkHttpClient by lazy {
                OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build()
            }

            return Retrofit.Builder()
                .baseUrl(url)
                .client(okHttp)
                .addCallAdapterFactory(FlowCallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        }
    }
}