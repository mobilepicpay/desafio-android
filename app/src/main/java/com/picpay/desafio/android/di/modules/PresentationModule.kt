package com.picpay.desafio.android.di.modules

import com.picpay.desafio.android.presentation.posts.viewmodel.PostsViewModel
import com.picpay.desafio.android.presentation.users.viewmodel.UsersViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { UsersViewModel(get()) }
    viewModel { PostsViewModel(get()) }
}
