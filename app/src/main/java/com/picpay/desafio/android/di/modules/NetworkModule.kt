package com.picpay.desafio.android.di.modules

import com.picpay.desafio.android.data.remote.PicPayService
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { OkHttpClient.Builder().build() }

    // PicPay API Retrofit
    single(named("picpay")) {
        Retrofit.Builder()
            .baseUrl("https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    single { get<Retrofit>(named("picpay")).create(PicPayService::class.java) }
}
