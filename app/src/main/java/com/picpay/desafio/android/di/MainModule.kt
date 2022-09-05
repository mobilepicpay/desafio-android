package com.picpay.desafio.android.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.picpay.desafio.android.data.repository.ContactRepository
import com.picpay.desafio.android.data.repository.ContactRepositoryImpl
import com.picpay.desafio.android.data.services.PicPayService
import com.picpay.desafio.android.domain.useCases.ListContactsUseCase
import com.picpay.desafio.android.domain.useCases.ListContactsUseCaseImpl
import com.picpay.desafio.android.presentation.viewModel.ContactListViewModel
import kotlinx.coroutines.Dispatchers
import okhttp3.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object MainModule {

    //CACHE vars
    private var CACHE_MAX_AGE_VALUE = 5
    private var CACHE_MAX_STALE_VALUE = 1
    private var HEADER_PRAGMA = "Pragma"
    private var HEADER_CACHE_CONTROL = "Cache-Control"
    private const val CACHE_SIZE = (5 * 1024 * 1024).toLong()

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

        fun cacheProvider(context: Context) = Cache(context.cacheDir, CACHE_SIZE)

        fun cacheInterceptorProvider() = Interceptor { chain ->
            val request: Request = chain.request()
            val cacheControl = CacheControl.Builder()
                .maxAge(CACHE_MAX_AGE_VALUE, TimeUnit.MINUTES)
                .build()
            request.newBuilder()
                .removeHeader(HEADER_PRAGMA)
                .header(HEADER_CACHE_CONTROL, cacheControl.toString())
                .build()
            chain.proceed(request)
        }

        fun offlineCacheInterceptorProvider() = Interceptor { chain ->
            try {
                chain.proceed(chain.request())
            } catch (e: Exception) {
                val cacheControl = CacheControl.Builder()
                    .onlyIfCached()
                    .maxStale(CACHE_MAX_STALE_VALUE, TimeUnit.DAYS)
                    .build()
                val offlineRequest: Request = chain.request().newBuilder()
                    .cacheControl(cacheControl)
                    .removeHeader(HEADER_PRAGMA)
                    .build()
                chain.proceed(offlineRequest)
            }
        }

        fun okHttpProvider(cache: Cache) =
            OkHttpClient.Builder()
                .addNetworkInterceptor(cacheInterceptorProvider())
                .addInterceptor(offlineCacheInterceptorProvider())
                .cache(cache)
                .build()


        single {
            gsonProvider()
        }.bind<Gson>()
        single {
            cacheProvider(get())
        }
        single {
            okHttpProvider(get())
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