package com.picpay.desafio.android.data.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.picpay.desafio.android.data.UserRepository
import com.picpay.desafio.android.data.UserRepositoryImpl
import com.picpay.desafio.android.data.network.PicPayService
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.koin.core.component.KoinComponent
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

val dataModule = module {
    single<UserRepository> { UserRepositoryImpl(get()) }
}

class PicPayServiceFactory : KoinComponent {
    private val baseUrl = "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/"

    private val gson: Gson by lazy { GsonBuilder().create() }

    val cacheSize = (10 * 1021 * 1024).toLong() // 10MB
    val cache = Cache(File("cacheDir"), cacheSize)
    val okHttpClient = OkHttpClient.Builder()
        .cache(cache)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .baseUrl(baseUrl)
        .build()

    fun picPayServiceModule(): Module {
        return module {
            single { retrofit.create(PicPayService::class.java) }
        }
    }
}