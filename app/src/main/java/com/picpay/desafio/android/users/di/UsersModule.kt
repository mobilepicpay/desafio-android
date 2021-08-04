package com.picpay.desafio.android.users.di

import com.picpay.desafio.android.users.repo.UsersApi
import com.picpay.desafio.android.users.viewmodels.UsersListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val usersListModule = module {

    factory { provideUsersApi(get()) }

    viewModel { UsersListViewModel(get()) }
}

fun provideUsersApi(retrofit: Retrofit): UsersApi = retrofit.create(UsersApi::class.java)