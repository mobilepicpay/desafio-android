package com.picpay.desafio.android.modules

import com.google.gson.GsonBuilder
import com.picpay.desafio.android.data.service.PicPayService
import com.picpay.desafio.android.domain.repository.PicPayRepository
import com.picpay.desafio.android.domain.repository.PicPayRepositoryImpl
import okhttp3.OkHttpClient
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitModule {
    private const val URL = "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/"
    private val gsonBuilder = GsonBuilder()

    fun load() {
        loadKoinModules(repositoryModule() + networkModule())
    }

    private fun repositoryModule(): Module {
        return module {
            single<PicPayRepository> { PicPayRepositoryImpl(get()) }
        }
    }

    private fun networkModule(): Module {
        return module {
            single<PicPayService> { createService(URL, get()) }
            single { createOkHttpBuilder() }
        }
    }

    private inline fun <reified T> createService(
        url: String,
        client: OkHttpClient
    ): T {
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
            .client(client)
            .build()
            .create(T::class.java)
    }

    private fun createOkHttpBuilder(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }
}