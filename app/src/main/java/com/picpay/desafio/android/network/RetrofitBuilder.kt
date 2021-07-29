package com.picpay.desafio.android.utils

import com.picpay.desafio.android.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitBuilder {

    companion object {
        fun buildRetrofitInstance(): Retrofit {
            return Retrofit.Builder().run {
                baseUrl(BuildConfig.BASE_URL)
                addConverterFactory(GsonConverterFactory.create())
                build()
            }
        }
    }
}
