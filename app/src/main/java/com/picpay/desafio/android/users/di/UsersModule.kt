package com.picpay.desafio.android.users.di

import com.picpay.desafio.android.users.UserListAdapter
import com.picpay.desafio.android.users.repo.UsersApi
import com.picpay.desafio.android.users.viewmodels.UsersListViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val usersListModule = module {

    factory { get<Retrofit>().create(UsersApi::class.java) }

    factory { UserListAdapter() }

    viewModel { UsersListViewModel(get(), Dispatchers.IO) }
}