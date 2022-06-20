package com.picpay.desafio.network.di

import com.picpay.desafio.network.BuildConfig
import com.picpay.desafio.network.NetworkClientProvider.buildOkHttpClient
import com.picpay.desafio.network.NetworkClientProvider.buildRetrofitClient
import org.koin.dsl.module

val networkModule = module {
    single {
        buildRetrofitClient(
            buildOkHttpClient(),
            BuildConfig.API_BASE_URL
        )
    }
}
