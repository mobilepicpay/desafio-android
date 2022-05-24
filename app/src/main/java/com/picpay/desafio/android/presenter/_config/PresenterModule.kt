package com.picpay.desafio.android.presenter._config

import com.picpay.desafio.android.presenter.viewmodel.ContactViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object PresenterModule {

    val module = module {
        viewModel { ContactViewModel(get()) }
    }
}