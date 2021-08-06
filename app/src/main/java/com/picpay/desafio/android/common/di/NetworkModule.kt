package com.picpay.desafio.android.common.di

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.picpay.desafio.android.BuildConfig
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    val myCacheSize = (5 * 1024 * 1024).toLong()
    val cacheControlMax5 = "public, max-age = 5"
    val cacheControlOnlyCached = "public, only-if-cached, max-stale=${60 * 60 * 24 * 7}"

    factory { Cache(androidContext().cacheDir, myCacheSize) }

    factory {
        OkHttpClient.Builder()
            .cache(get())
            .addInterceptor { chain ->
                var request = chain.request()
                request = if (hasNetwork(androidContext())!!)
                    request.newBuilder().header("Cache-Control", cacheControlMax5).build()
                else
                    request.newBuilder().header("Cache-Control", cacheControlOnlyCached).build()
                chain.proceed(request)
            }
            .build()
    }

    single {
        Retrofit.Builder().baseUrl(BuildConfig.API_URL).client(get())
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
}

@SuppressLint("MissingPermission")
fun hasNetwork(context: Context): Boolean? {
    var isConnected: Boolean? = false
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
    if (activeNetwork != null && activeNetwork.isConnected)
        isConnected = true
    return isConnected
}