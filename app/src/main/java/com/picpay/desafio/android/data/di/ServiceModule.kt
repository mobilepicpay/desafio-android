package com.picpay.desafio.android.data.di

import com.google.gson.Gson
import com.picpay.desafio.android.data.features.contacts.service.ContactsService
import com.picpay.desafio.android.data.utils.Constants
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceModule {
    fun getModule() = service

    private inline fun <reified T> createService(
        client: OkHttpClient,
        converterFactory: Gson,
    ): T = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .client(client)
        .addConverterFactory(
            GsonConverterFactory.create(
                converterFactory
            )
        )
        .build()
        .create(T::class.java)

    private val service = module {
        single<ContactsService> { createService(get(), get()) }
    }
}
