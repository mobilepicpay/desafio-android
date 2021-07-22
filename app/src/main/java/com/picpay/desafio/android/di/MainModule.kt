package com.picpay.desafio.android.di

import com.picpay.desafio.android.data.local.datasource.UserLocalDataSource
import com.picpay.desafio.android.data.local.datasource.UserLocalDataSourceImpl
import com.picpay.desafio.android.data.local.db.DatabasePicPay
import com.picpay.desafio.android.data.local.db.DatabasePicPay.Companion.createDatabase
import com.picpay.desafio.android.data.remote.Client
import com.picpay.desafio.android.data.remote.datasource.UserRemoteDataSource
import com.picpay.desafio.android.data.remote.datasource.UserRemoteDataSourceImpl
import com.picpay.desafio.android.data.repository.UserDataRepository
import com.picpay.desafio.android.data.repository.UserDataRepositoryImpl
import com.picpay.desafio.android.ui.viewmodel.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    factory {
        Client()
    }

    factory {
        get<DatabasePicPay>().userDao()
    }

    single { createDatabase(context = androidContext()) }

    viewModel {
        MainViewModel(
            userDataRepository = get()
        )
    }

    factory<UserDataRepository> {
        UserDataRepositoryImpl(
            userRemoteDataSource = get(),
            localDataSource = get()
        )
    }

    factory<UserRemoteDataSource> {
        UserRemoteDataSourceImpl(
            service = get<Client>().getUserService()
        )
    }

    factory<UserLocalDataSource> {
        UserLocalDataSourceImpl(
            userDao = get()
        )
    }

}

