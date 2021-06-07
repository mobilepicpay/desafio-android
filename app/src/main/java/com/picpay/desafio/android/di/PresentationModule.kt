package com.picpay.desafio.android.di

import com.picpay.desafio.android.features.MainViewModel
import com.picpay.desafio.android.features.UserListAdapter
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    factory { UserListAdapter() }

    viewModel { MainViewModel(usersUseCases = get()) }
}