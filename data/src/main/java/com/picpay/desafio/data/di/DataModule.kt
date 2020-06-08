package com.picpay.desafio.data.di

import androidx.room.Room
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.picpay.desafio.data.BuildConfig
import com.picpay.desafio.data.apiservice.UserService
import com.picpay.desafio.data.db.PicPayDatabase
import com.picpay.desafio.data.repositories.UserRepositoryImp
import com.picpay.desafio.data.utils.Constants
import com.picpay.desafio.domain.repositories.UserRepository
import com.picpay.desafio.domain.usecases.GetUserDetail
import com.picpay.desafio.domain.usecases.GetUserList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@ExperimentalCoroutinesApi
object DataModule {

    fun getModules() = arrayListOf(
        apiModule,
        repositoryModule,
        networkModule,
        databaseModule,
        usecaseModule
    )

    private val apiModule = module {
        single { get<Retrofit>().create(UserService::class.java) }
    }

    private val repositoryModule= module {
        factory { UserRepositoryImp(get(), get(), get()) as UserRepository}
    }

    private val networkModule = module {

        single {
            OkHttpClient.Builder().apply {
                readTimeout(20, TimeUnit.SECONDS)
                connectTimeout(20, TimeUnit.SECONDS)
                if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                        addInterceptor(this)
                    }
                }
            }.build()
        }

        single {
            Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
        }
    }

    private val databaseModule = module {
        single {
            Room.databaseBuilder(
                get(),
                PicPayDatabase::class.java,
                Constants.DATABASE_NAME
            )
                .build()
        }
        single { get<PicPayDatabase>().userDao() }
        single { get<PicPayDatabase>().keyValueDao() }
    }

    private val usecaseModule = module {
        factory { GetUserList(get()) }
        factory { GetUserDetail(get()) }
    }
}