package com.picpay.desafio.android.shared.di

import com.google.gson.GsonBuilder
import com.picpay.desafio.android.shared.coroutine.CoroutineDispatching
import com.picpay.desafio.android.shared.coroutine.DefaultDispatching
import com.picpay.desafio.android.shared.data.PicPayApi
import okhttp3.OkHttpClient
import org.koin.core.KoinApplication
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SharedKoin: KoinModule() {

    private val retrofitModule = module {
        single<Retrofit> {
            val url = "http://careers.picpay.com/tests/mobdev/"
            val gson = GsonBuilder().create()
            val okHttp = OkHttpClient.Builder().build()

            Retrofit.Builder()
                .baseUrl(url)
                .client(okHttp)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }

        single<PicPayApi> {
            get<Retrofit>().create(PicPayApi::class.java)
        }
    }

    private val coroutineModule = module {
        single<CoroutineDispatching> { DefaultDispatching() }
    }

    override fun loadModule(koinApplication: KoinApplication) {
        koinApplication.apply {
            loadKoinModules(retrofitModule + coroutineModule)
        }
    }
}