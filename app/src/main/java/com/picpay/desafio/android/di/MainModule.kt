package com.picpay.desafio.android.di

import com.picpay.desafio.android.remote.repository.PicPayRepository
import com.picpay.desafio.android.remote.service.RetrofitService
import com.picpay.desafio.android.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    single {
        PicPayRepository(RetrofitService(get()).getPicPayService())
    }

    viewModel {
        MainViewModel(get())
    }
}