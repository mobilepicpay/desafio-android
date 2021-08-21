package com.picpay.desafio.android.di

import com.picpay.desafio.android.BuildConfig
import com.picpay.desafio.android.network.NetworkDI
import com.picpay.desafio.android.repository.UserRepositoryImpl
import com.picpay.desafio.android.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val networkModule = NetworkDI(BuildConfig.BASE_URL).get()

val repositoryModule = module {
    factory { UserRepositoryImpl( service = get(), networkConfig = get()) }
}

val viewModelModule = module {
    viewModel {
        UserViewModel(repository = get())
    }
}