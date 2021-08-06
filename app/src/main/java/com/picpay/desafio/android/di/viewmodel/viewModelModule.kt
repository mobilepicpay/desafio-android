package com.picpay.desafio.android.di.viewmodel

import com.picpay.desafio.android.viewmodels.UserViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@ExperimentalCoroutinesApi
val viewModelModule = module {
    viewModel {
        UserViewModel(repository = get())
    }
}