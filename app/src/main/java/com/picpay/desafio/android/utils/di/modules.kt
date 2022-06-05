package com.picpay.desafio.android.utils.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.picpay.desafio.android.ui.home.UserViewModel
import com.picpay.desafio.android.utils.Constants
import com.picpay.desafio.android.utils.Constants.SHARED_PREFERENCE_NAME
import com.picpay.desafio.android.utils.service.PicPayService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val viewModelModules = module{
    viewModel { UserViewModel(get(), get())}
}

val networkModule = module {
    single { createHttpClient() }
    single { retrofitClient(get())}
    single { createNetworkApi(get()) }
}

val sharedPreferenceModules = module{
    single { provideSharedPref(androidApplication()) }
}


fun provideSharedPref(app: Application): SharedPreferences {
    return app.applicationContext.getSharedPreferences(
        SHARED_PREFERENCE_NAME,
        Context.MODE_PRIVATE
    )
}

fun retrofitClient(client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .client(client)
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun createHttpClient(): OkHttpClient {
    val loggin = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    val client = OkHttpClient.Builder().apply{
        addInterceptor(loggin)
        readTimeout(5 * 60, TimeUnit.SECONDS)
    }
    return client.build()
}

fun createNetworkApi(retrofit: Retrofit): PicPayService {
    return retrofit.create(PicPayService::class.java)
}
