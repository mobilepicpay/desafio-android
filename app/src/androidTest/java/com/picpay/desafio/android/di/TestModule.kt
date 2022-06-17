package com.picpay.desafio.android.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.picpay.desafio.android.data.remote.PicPayApi
import com.picpay.desafio.android.data.remote.UserRemoteDataSource
import com.picpay.desafio.android.data.repository.ContactDataRepository
import com.picpay.desafio.android.domain.repository.ContactRepository
import com.picpay.desafio.android.domain.useCases.ListContactsUseCase
import com.picpay.desafio.android.domain.useCases.ListContactsUseCaseImpl
import com.picpay.desafio.android.presentation.viewModels.ContactViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.scope.Scope
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun Scope.getRetrofit() = get<Retrofit>()

object TestModuleInitializer {

    private val networkModule = module {
        fun provideBaseUrl() = "http://localhost:8080"
        fun provideGson() = GsonBuilder().create()
        fun provideOkHttp() = OkHttpClient.Builder().build()
        fun provideRetrofit(baseUrl: String, okHttp: OkHttpClient, gson: Gson) = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        single { provideBaseUrl() }
        single { provideGson() }
        single { provideOkHttp() }
        single { provideRetrofit(get(), get(), get()) }
        single { getRetrofit().create(PicPayApi::class.java) }
    }

    private val dataModule = module {
        single { UserRemoteDataSource(get()) }
        single<ContactRepository> { ContactDataRepository(get()) }
    }

    private val useCasesModule = module {
        factory<ListContactsUseCase> { ListContactsUseCaseImpl(get()) }
    }

    private val viewModelsModule = module {
        viewModel { ContactViewModel(get()) }
    }

    fun init() = loadKoinModules(
        listOf(networkModule, dataModule, useCasesModule, viewModelsModule)
    )
}