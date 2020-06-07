package com.picpay.desafio.android.di

import androidx.room.Room
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.picpay.desafio.android.BuildConfig
import com.picpay.desafio.android.features.user.detail.viewmodel.UserDetailViewModel
import com.picpay.desafio.android.features.user.list.adapter.UserListAdapter
import com.picpay.desafio.android.features.user.list.viewmodel.UserListViewModel
import com.picpay.desafio.android.features.user.repository.UserRepository
import com.picpay.desafio.android.features.user.network.api.UserService
import com.picpay.desafio.android.room.db.PicPayDatabase
import com.picpay.desafio.android.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object KoinModule {

    fun getModules() = arrayListOf(
        viewModelModule, repositoryModule, adapterModule, netModule, databaseModule
    )

    private val viewModelModule = module {
        factory { UserListViewModel(get()) }
        factory { UserDetailViewModel(get()) }
    }

    private val repositoryModule = module {
        factory { UserRepository(get(), get(), get()) }
    }

    private val adapterModule = module {
        factory { UserListAdapter() }
    }

    private val netModule = module {

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

        single { get<Retrofit>().create(UserService::class.java) }
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
}