package com.picpay.desafio.android.data.remote.util

import com.picpay.desafio.android.domain.result.ResultWrapper
import com.picpay.desafio.data.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

suspend fun <T> call(apiCall: suspend () -> T): ResultWrapper<T> {
    return withContext(Dispatchers.IO) {
        try {
            ResultWrapper.Success(apiCall.invoke())
        } catch (exception: Exception) {
            ResultWrapper.Error(null)
        }
    }
}

val okHttp: OkHttpClient by lazy {
    val interceptor = HttpLoggingInterceptor()
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

    OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()
}

inline fun <reified T> createWebService(): T {
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttp)
        .build()
        .create(T::class.java)
}
