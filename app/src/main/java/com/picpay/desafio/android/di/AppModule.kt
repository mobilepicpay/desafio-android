package com.picpay.desafio.android.di

import com.picpay.desafio.android.data.repository.UserRepositoryImpl
import com.picpay.desafio.android.domain.repository.UserRepository
import com.picpay.desafio.android.domain.usecase.GetUsersUseCase
import com.picpay.desafio.android.presentation.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    factory<UserRepository> { UserRepositoryImpl(get(), get()) }

    factory { GetUsersUseCase(get()) }

    viewModel { UserViewModel(get()) }
}
