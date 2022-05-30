package com.picpay.desafio.android._config

import com.picpay.desafio.android.data._config.network.ClientFactory
import com.picpay.desafio.android.data.contact.ContactAPI
import okhttp3.mockwebserver.MockWebServer
import org.koin.dsl.module
import retrofit2.Retrofit

object KoinModules {

    val retrofitModule = module {
        single { ClientFactory.buildOkHttpClient() }
        single {
            ClientFactory.buildRetrofit(client = get())
        }
    }

    val mockRetrofitModule = module {
        single { MockWebServer() }
        single { ClientFactory.buildOkHttpClient() }
        single {
            ClientFactory.buildRetrofit(
                host = get<MockWebServer>().url("/").toString(),
                client = get()
            )
        }
    }

    val APIsModule = module {
        single { get<Retrofit>().create(ContactAPI::class.java) }
    }

}