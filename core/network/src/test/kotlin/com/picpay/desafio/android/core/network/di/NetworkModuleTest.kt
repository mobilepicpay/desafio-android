package com.picpay.desafio.android.core.network.di

import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import org.junit.Test
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.test.verify.verify
import retrofit2.Retrofit

@OptIn(KoinExperimentalAPI::class)
class NetworkModuleTest {

    @Test
    fun checkNetworkModule() {
        networkModule.verify(
            extraTypes = listOf(
                CoroutineDispatcher::class,
                OkHttpClient::class,
                Retrofit::class
            )
        )
    }
}