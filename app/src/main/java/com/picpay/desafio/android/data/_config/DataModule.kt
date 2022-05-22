package com.picpay.desafio.android.data._config

import com.picpay.desafio.android.data.contact.ContactAPI
import com.picpay.desafio.android.data.contact.ContactRemoteDataSource
import com.picpay.desafio.android.data.contact.ContactRepositoryImpl
import com.picpay.desafio.android.domain.repository.ContactRepository
import org.koin.dsl.module
import retrofit2.Retrofit

object DataModule {

    val module = module {
        single { ClientFactory.buildOkHttpClient() }
        single {
            ClientFactory.buildRetrofit(client = get())
        }
        single { get<Retrofit>().create(ContactAPI::class.java) }
        single { ContactRemoteDataSource(get()) }
        single<ContactRepository> { ContactRepositoryImpl(get()) }
    }
}