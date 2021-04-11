package com.picpay.desafio.android.shared.di

import androidx.room.Room
import com.google.gson.GsonBuilder
import com.picpay.desafio.android.shared.BuildConfig
import com.picpay.desafio.android.shared.coroutine.CoroutineService
import com.picpay.desafio.android.shared.coroutine.DefaultCoroutineService
import com.picpay.desafio.android.shared.data.local.PicPayDataBase
import com.picpay.desafio.android.shared.data.remote.PicPayRemoteDataSource
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.KoinApplication
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SharedKoin : KoinModule() {

    private val retrofitModule = module {
        single<Retrofit> {
            val url = "http://careers.picpay.com/tests/mobdev/"
            val gson = GsonBuilder().create()

            val okHttp = OkHttpClient.Builder().also {
                if (BuildConfig.DEBUG) {
                    it.addInterceptor(
                        HttpLoggingInterceptor()
                            .apply { level = HttpLoggingInterceptor.Level.BODY }
                    )
                }
            }.build()

            Retrofit.Builder()
                .baseUrl(url)
                .client(okHttp)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }

        single<PicPayRemoteDataSource> {
            get<Retrofit>().create(PicPayRemoteDataSource::class.java)
        }
    }

    private val roomModule = module {
        single { Room.databaseBuilder(get(), PicPayDataBase::class.java, "database-name").build() }

        factory { get<PicPayDataBase>().localDataSource() }
    }

    private val coroutineModule = module {
        single<CoroutineService> { DefaultCoroutineService() }
    }

    override fun loadModule(koinApplication: KoinApplication) {
        koinApplication.apply {
            loadKoinModules(retrofitModule + roomModule + coroutineModule)
        }
    }
}