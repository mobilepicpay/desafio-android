package com.picpay.desafio.android.di

import androidx.room.Room
import com.picpay.desafio.android.data.api.PicPayService
import com.picpay.desafio.android.data.local.AppDatabase
import com.picpay.desafio.android.data.repository.UserRepositoryImpl
import com.picpay.desafio.android.domain.repository.UserRepository
import com.picpay.desafio.android.ui.UserViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "picpay-db")
            .fallbackToDestructiveMigration(false)
            .build()
    }

    single { get<AppDatabase>().userDao() }

    single {
        val baseUrl = System.getProperty("baseUrl")
            ?: "https://683cfcbc199a0039e9e3df18.mockapi.io/api/v1/"

        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().build())
            .build()
            .create(PicPayService::class.java)
    }

    single<UserRepository> { UserRepositoryImpl(get(), get()) }

    viewModel { UserViewModel(get()) }
}
