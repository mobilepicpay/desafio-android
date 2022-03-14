package com.picpay.desafio.android.di

import androidx.room.Room
import com.picpay.desafio.android.database.dao.UserDao
import com.picpay.desafio.android.database.UserDatabase
import com.picpay.desafio.android.repository.local.UserLocalDataSource
import com.picpay.desafio.android.repository.local.UserLocalDataSourceImp
import com.picpay.desafio.android.repository.remote.UserRemoteDataSource
import com.picpay.desafio.android.repository.remote.UserRemoteDataSourceImp
import com.picpay.desafio.android.repository.UserRepository
import com.picpay.desafio.android.repository.UserRepositoryImp
import com.picpay.desafio.android.ui.UserListViewModel
import com.picpay.desafio.android.repository.remote.webclient.PicPayService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val URL_BASE = "chave"
private const val NOME_BANCO_DE_DADOS = "user.db"

val retrofitModule = module {
    single<Retrofit> {
        val property = getProperty<String>(URL_BASE)
        Retrofit.Builder()
            .baseUrl(property)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<PicPayService> { get<Retrofit>().create(PicPayService::class.java) }

    single<OkHttpClient> {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }
}

val databaseModule = module {
    single<UserDatabase> {
        Room.databaseBuilder(
            get(),
            UserDatabase::class.java,
            NOME_BANCO_DE_DADOS
        ).build()
    }
}

val daoModule = module {
single<UserDao> { get<UserDatabase>().userDao()}
}

val viewModule = module {
    viewModel { UserListViewModel(get())}
}

val repositoryModule = module {
    single<UserRepository> { UserRepositoryImp(get(), get())}
}

val localModule = module {
    single<UserLocalDataSource> { UserLocalDataSourceImp(get())}
}

val remoteModule = module {
    single<UserRemoteDataSource> { UserRemoteDataSourceImp(get())}
}

val appModules = listOf(
    retrofitModule,
    viewModule,
    repositoryModule,
    daoModule,
    databaseModule,
    localModule,
    remoteModule
)

