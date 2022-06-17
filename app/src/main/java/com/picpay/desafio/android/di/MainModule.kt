package com.picpay.desafio.android.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.picpay.desafio.android.data.remote.PicPayApi
import com.picpay.desafio.android.data.remote.UserRemoteDataSource
import com.picpay.desafio.android.data.repository.ContactDataRepository
import com.picpay.desafio.android.domain.repository.ContactRepository
import com.picpay.desafio.android.domain.useCases.ListContactsUseCase
import com.picpay.desafio.android.domain.useCases.ListContactsUseCaseImpl
import com.picpay.desafio.android.presentation.viewModels.ContactViewModel
import okhttp3.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.scope.Scope
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun Scope.getRetrofit() = get<Retrofit>()

object MainModuleInitializer {

    private var CACHE_MAX_AGE_VALUE = 5
    private var CACHE_MAX_STALE_VALUE = 1
    private var HEADER_PRAGMA = "Pragma"
    private var HEADER_CACHE_CONTROL = "Cache-Control"
    private const val CACHE_SIZE = (5 * 1024 * 1024).toLong()

    private val networkModule = module {

        fun provideBaseUrl() = "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/"

        fun provideGson() = GsonBuilder().create()

        fun provideCache(context: Context) = Cache(context.cacheDir, CACHE_SIZE)

        fun provideCacheInterceptor() = Interceptor { chain ->
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

        fun provideOfflineCacheInterceptor() = Interceptor { chain ->
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

        fun provideOkHttp(cache: Cache) = OkHttpClient.Builder()
            .addNetworkInterceptor(provideCacheInterceptor())
            .addInterceptor(provideOfflineCacheInterceptor())
            .cache(cache)
            .build()

        fun provideRetrofit(baseUrl: String, okHttp: OkHttpClient, gson: Gson) = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        single { provideBaseUrl() }
        single { provideGson() }
        single { provideCache(get()) }
        single { provideOkHttp(get()) }
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