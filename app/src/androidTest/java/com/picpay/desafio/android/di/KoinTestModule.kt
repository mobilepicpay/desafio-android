package com.picpay.desafio.android.di

import com.picpay.desafio.android.domain.repository.UserRepository
import com.picpay.desafio.android.repository.FakeUserRepository
import com.picpay.desafio.android.ui.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val testModule = module {
    single<UserRepository> {
        FakeUserRepository(shouldFail = false)
    }

    viewModel {
        UserViewModel(get())
    }
}