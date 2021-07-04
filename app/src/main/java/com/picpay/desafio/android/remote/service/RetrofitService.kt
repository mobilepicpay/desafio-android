package com.picpay.desafio.android.remote.service

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.picpay.desafio.android.utls.RemoteUtls
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService(context: Context) {

    private val url = "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/"
    private val gson: Gson = GsonBuilder().create()
    private val cacheSize = (10 * 1024 * 1024).toLong()
    private val cache = Cache(context.cacheDir, cacheSize)

    private val okHttpClient = OkHttpClient.Builder()
        .cache(cache)
        .addInterceptor { chain ->
            var request = chain.request()
            request =
                if (RemoteUtls.isConnectionAvailable(context))
                    request.newBuilder()
                        .header("Cache-Control", "public, max-age=" + 5)
                        .build()
                else
                    request.newBuilder()
                        .header(
                            "Cache-Control",
                            "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                        ).build()
            chain.proceed(request)
        }
        .build()


    private fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    fun getPicPayService(): PicPayService = getInstance().create(PicPayService::class.java)
}