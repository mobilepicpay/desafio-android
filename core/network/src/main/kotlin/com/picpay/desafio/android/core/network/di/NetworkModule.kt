package com.picpay.desafio.android.core.network.di

import com.picpay.desafio.android.core.network.BuildConfig
import com.picpay.desafio.android.core.network.NetworkClient.retrofitBuilder
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val networkModule = module {

    single { retrofitBuilder(BuildConfig.BASE_URL) }

    single<CoroutineDispatcher> { Dispatchers.IO }

}