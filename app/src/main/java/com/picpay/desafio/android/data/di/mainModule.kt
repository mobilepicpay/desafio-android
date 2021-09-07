package com.picpay.desafio.android.data.di

import com.picpay.desafio.android.data.repository.Repository
import com.picpay.desafio.android.ui.main.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    factory {
        Repository()
    }
    viewModel {
        MainViewModel(repository = get())
    }
}