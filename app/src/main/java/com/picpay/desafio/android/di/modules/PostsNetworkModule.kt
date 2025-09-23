package com.picpay.desafio.android.di.modules

import com.picpay.desafio.android.data.remote.PostsService
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val postsNetworkModule = module {
    // JSONPlaceholder API Retrofit (usando o mesmo OkHttpClient)
    single(named("jsonplaceholder")) {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get<OkHttpClient>())
            .build()
    }

    single { get<Retrofit>(named("jsonplaceholder")).create(PostsService::class.java) }
}
