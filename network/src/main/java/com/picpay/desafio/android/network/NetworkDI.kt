package com.picpay.desafio.android.network

import com.picpay.desafio.android.network.config.NetworkConfig
import com.picpay.desafio.android.network.config.RetrofitConfig
import com.picpay.desafio.android.network.util.ConnectivityInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetworkDI(private val baseUrl: String) {

    fun get() : Module {
        return module {
            single { loggingInterceptor() }
            single { ConnectivityInterceptor(context = get()) }
            single { GsonConverterFactory.create() }
            single { okHttpClient(interceptors = listOf(get<ConnectivityInterceptor>(), get<HttpLoggingInterceptor>())) }
            single {
                RetrofitConfig(
                    baseUrl = baseUrl,
                    okHttpClient = get(),
                    gsonConverterFactory = get()
                )
            }

            factory { NetworkConfig() }
        }

    }

    private fun okHttpClient(interceptors: List<Interceptor>): OkHttpClient {
        val builder = OkHttpClient().newBuilder()
        interceptors.forEach { builder.addInterceptor(it) }
        builder.connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        builder.readTimeout(TIMEOUT, TimeUnit.SECONDS)
        return builder.build()
    }

    private fun loggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        HttpLoggingInterceptor().level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    private companion object {
        const val TIMEOUT = 1000L
    }
}