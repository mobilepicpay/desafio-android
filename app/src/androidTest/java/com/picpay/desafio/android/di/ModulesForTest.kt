package com.picpay.desafio.android.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.picpay.desafio.android.data.repository.ContactRepository
import com.picpay.desafio.android.data.repository.ContactRepositoryImpl
import com.picpay.desafio.android.data.services.PicPayService
import com.picpay.desafio.android.domain.useCases.ListContactsUseCase
import com.picpay.desafio.android.domain.useCases.ListContactsUseCaseImpl
import com.picpay.desafio.android.presentation.viewModel.ContactListViewModel
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ModulesForTest {
    private const val BASE_URL = "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/"

    fun load() {
        loadKoinModules(
            networkModule() +
                    dataModule() +
                    useCaseModule() +
                    viewModelsModule()
        )
    }

    private fun networkModule() = module {

        fun gsonProvider() = GsonBuilder().create()

        fun okHttpProvider() =
            OkHttpClient.Builder().build()

        single {
            gsonProvider()
        }.bind<Gson>()
        single {
            okHttpProvider()
        }.bind()
        single {
            createService<PicPayService>(get(), get())
        }.bind()
    }

    private fun dataModule() = module {
        single { ContactRepositoryImpl(get(), Dispatchers.IO) }.bind<ContactRepository>()
    }

    private fun useCaseModule() = module {
        factory<ListContactsUseCase> {
            ListContactsUseCaseImpl(get())
        }
    }

    private fun viewModelsModule() = module {
        viewModel { ContactListViewModel(get()) }.bind()
    }

    private inline fun <reified T> createService(
        okHttp: OkHttpClient,
        gson: Gson
    ): T {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(T::class.java)
    }
}