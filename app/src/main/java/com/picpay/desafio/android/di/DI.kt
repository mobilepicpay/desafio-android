package com.picpay.desafio.android.di

import com.picpay.desafio.android.BuildConfig
import com.picpay.desafio.android.local.LocalDI
import com.picpay.desafio.android.network.NetworkDI
import com.picpay.desafio.android.network.config.RetrofitConfig
import com.picpay.desafio.android.repository.UserRepository
import com.picpay.desafio.android.repository.UserRepositoryImpl
import com.picpay.desafio.android.service.UserService
import com.picpay.desafio.android.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val networkModule = NetworkDI(BuildConfig.BASE_URL).get()

val serviceModule = module {
    factory { get<RetrofitConfig>().create(UserService::class.java) }
}

val dataBase = LocalDI().get()

val repositoryModule = module {
    factory<UserRepository> { UserRepositoryImpl( service = get(), networkConfig = get(), db = get()) }
}

val viewModelModule = module {
    viewModel {
        UserViewModel(repository = get())
    }
}