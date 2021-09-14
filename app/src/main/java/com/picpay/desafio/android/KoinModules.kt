package com.picpay.desafio.android

import androidx.room.Room
import com.google.gson.GsonBuilder
import com.picpay.desafio.android.interactor.PicPayInteractor
import com.picpay.desafio.android.interactor.PicPayInteractorImpl
import com.picpay.desafio.android.repository.PicPayRepository
import com.picpay.desafio.android.repository.PicPayRepositoryImpl
import com.picpay.desafio.android.repository.local.PicPayDatabase
import com.picpay.desafio.android.repository.remote.PicPayService
import com.picpay.desafio.android.view.MainViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object KoinModules {

    private const val URL = "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/"

    private val apis = module {
        single { OkHttpClient.Builder().build() }
        single { GsonBuilder().create() }

        single {
            Retrofit.Builder()
                .baseUrl(URL)
                .client(get())
                .addConverterFactory(GsonConverterFactory.create(get()))
                .build()
        }

        single { get<Retrofit>().create(PicPayService::class.java) }
    }

    private val databases = module {
        single {
            Room.databaseBuilder(
                androidContext(),
                PicPayDatabase::class.java,
                PicPayDatabase.NAME
            ).build()
        }
    }

    private val repositories = module {
        single { PicPayRepositoryImpl(get(), get()) as PicPayRepository }
    }

    private val interactors = module {
        single { PicPayInteractorImpl(get()) as PicPayInteractor }
    }

    private val viewModels = module {
        viewModel { MainViewModel(get()) }
    }

    val all = apis + databases + repositories + interactors + viewModels
}
