package com.picpay.desafio.android.di

import com.picpay.desafio.android.user.network.UserService
import com.picpay.desafio.android.network.RetrofitFactory
import com.picpay.desafio.android.user.repository.UserRepository
import com.picpay.desafio.android.user.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    factory { UserRepository(get()) }
    factory { RetrofitFactory.build(UserService::class.java) }
    viewModel { UserViewModel(get()) }
}