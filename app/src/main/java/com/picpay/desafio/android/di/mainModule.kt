package com.picpay.desafio.android.di

import com.picpay.desafio.android.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    viewModel { UserViewModel(get()) }
}