package com.picpay.desafio.android.di

import com.google.gson.GsonBuilder
import com.picpay.desafio.android.data.api.PicPayApi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


fun configureNetworkForInstrumentationTest(baseTestApi: String) = module {

    single(override = true) {
        Retrofit.Builder()
            .baseUrl(baseTestApi)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }

    factory<PicPayApi>(override = true) { get<Retrofit>().create(PicPayApi::class.java) }
}