package com.picpay.desafio.android.di

import com.picpay.desafio.android.presentation.users.viewmodel.UsersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // Presentation - ViewModels
    viewModel { UsersViewModel() }
}
