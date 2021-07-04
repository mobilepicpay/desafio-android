package com.picpay.desafio.android.remote

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService private constructor() {

    companion object {

        private const val url = "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/"
        private val gson: Gson = GsonBuilder().create()
        private val okHttp = OkHttpClient.Builder().build()


        fun getInstance(): Retrofit{
            return Retrofit.Builder()
                .baseUrl(url)
                .client(okHttp)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
    }
}