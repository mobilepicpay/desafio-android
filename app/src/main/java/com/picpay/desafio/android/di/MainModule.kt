package com.picpay.desafio.android.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.picpay.desafio.android.data.remote.PicPayApi
import com.picpay.desafio.android.data.remote.UserRemoteDataSource
import com.picpay.desafio.android.data.repository.UserDataRepository
import com.picpay.desafio.android.domain.repository.UserRepository
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

val networkModule = module {
    fun provideGson() = GsonBuilder().create()
    fun provideOkHttp() = OkHttpClient.Builder().build()
    fun provideRetrofit(okHttp: OkHttpClient, gson: Gson) = Retrofit.Builder()
        .baseUrl(PicPayApi.BASE_URL)
        .client(okHttp)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
    single { provideGson() }
    single { provideOkHttp() }
    single { provideRetrofit(get(), get()) }
    single { getRetrofit().create(PicPayApi::class.java) }
}

val dataModule = module {
    single { UserRemoteDataSource(get()) }
    single<UserRepository> { UserDataRepository(get()) }
}

val useCasesModule = module {
    factory<ListContactsUseCase> { ListContactsUseCaseImpl(get()) }
}

val viewModelsModule = module {
    viewModel { ContactViewModel(get()) }
}

object MainModuleInitializer {
    fun init() = loadKoinModules(
        listOf(networkModule, dataModule, useCasesModule, viewModelsModule)
    )
}