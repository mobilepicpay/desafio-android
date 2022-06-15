package com.picpay.desafio.android.modules

import com.picpay.desafio.android.domain.repository.GetUserCase
import com.picpay.desafio.android.view.ui.MainActivity
import com.picpay.desafio.android.view.viewModel.ListUsersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ListUsersViewModel(get()) }
}

val repositoryModule = module {
    single { GetUserCase(get()) }
}

val uiModule = module {
    factory { MainActivity() }
}