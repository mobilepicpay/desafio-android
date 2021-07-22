package com.picpay.desafio.android.data.remote

import com.picpay.desafio.android.data.remote.api.PicPayService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Client {
    fun getUserService(): PicPayService {
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(PicPayService::class.java)
    }

    companion object {
        const val url = "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/"
    }
}