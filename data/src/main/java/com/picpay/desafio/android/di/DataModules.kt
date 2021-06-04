package com.picpay.desafio.android.di

import com.picpay.desafio.android.R
import com.picpay.desafio.android.local.database.UsersDataBase
import com.picpay.desafio.android.local.source.UsersCacheDataSource
import com.picpay.desafio.android.local.source.UsersCacheDataSourceImpl
import com.picpay.desafio.android.remote.api.UserApi
import com.picpay.desafio.android.remote.repository.UsersRepositoryImpl
import com.picpay.desafio.android.remote.source.RemoteDataSource
import com.picpay.desafio.android.remote.source.RemoteDataSourceImpl
import com.picpay.desafio.android.repository.UsersRepository
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val remoteDataSourceModule = module {
    factory { providesOkHttpClient() }
    single {
        createWebService<UserApi>(
            okHttpClient = get(),
            url = androidContext().getString(R.string.base_url)
        )
    }

    factory<RemoteDataSource> { RemoteDataSourceImpl(userApi = get()) }
}

fun providesOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T {
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(url)
        .client(okHttpClient)
        .build()
        .create(T::class.java)
}

val cacheDataModule = module {
    single { UsersDataBase.createDataBase(androidContext()) }
    factory<UsersCacheDataSource> { UsersCacheDataSourceImpl(usersDao = get()) }
}

val repositoryModule = module {
    factory<UsersRepository> {
        UsersRepositoryImpl(
            usersCacheDataSource = get(),
            remoteDataSource = get()
        )
    }
}

val dataModules = listOf(remoteDataSourceModule, repositoryModule, cacheDataModule)