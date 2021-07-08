package com.picpay.desafio.android.di

import com.picpay.desafio.android.data.PicPayService
import com.picpay.desafio.android.domain.FetchUseCase
import com.picpay.desafio.android.ui.presentation.UserMapper
import com.picpay.desafio.android.ui.presentation.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val userModule = module {
    factory<PicPayService> { get<Retrofit>().create(PicPayService::class.java) }

    factory { UserMapper() }

    factory { FetchUseCase(get()) }

    viewModel { UserViewModel(get(), get()) }
}