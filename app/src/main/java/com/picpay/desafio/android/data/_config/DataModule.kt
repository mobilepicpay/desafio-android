package com.picpay.desafio.android.data._config

import android.app.Application
import com.picpay.desafio.android.data._config.network.CacheInterceptor
import com.picpay.desafio.android.data._config.network.ClientFactory
import com.picpay.desafio.android.data.contact.ContactAPI
import com.picpay.desafio.android.data.contact.ContactRemoteDataSource
import com.picpay.desafio.android.data.contact.ContactRepositoryImpl
import com.picpay.desafio.android.domain.repository.ContactRepository
import okhttp3.Cache
import org.koin.dsl.module
import retrofit2.Retrofit
import java.io.File

object DataModule {

    val module = module {
        single {
            try {
                val httpCacheDirectory = File(get<Application>().cacheDir, "http_cache")
                val cache = Cache(httpCacheDirectory, ClientFactory.CACHE_SIZE)
                ClientFactory.buildOkHttpClient(cache) { addInterceptor(CacheInterceptor(get())) }
            } catch (e: Exception) {
                ClientFactory.buildOkHttpClient()
            }
        }
        single { ClientFactory.buildRetrofit(client = get()) }
        single { get<Retrofit>().create(ContactAPI::class.java) }
        single { ContactRemoteDataSource(get()) }
        single<ContactRepository> { ContactRepositoryImpl(get()) }
    }
}