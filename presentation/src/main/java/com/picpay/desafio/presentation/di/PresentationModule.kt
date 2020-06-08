package com.picpay.desafio.presentation.di

import com.picpay.desafio.presentation.features.user.detail.viewmodel.UserDetailViewModel
import com.picpay.desafio.presentation.features.user.list.adapter.UserListAdapter
import com.picpay.desafio.presentation.features.user.list.viewmodel.UserListViewModel
import org.koin.dsl.module

object PresentationModule {

    fun getModules() = arrayListOf(
        viewModelModule,
        adapterModule
    )

    private val viewModelModule = module {
        factory { UserListViewModel(get()) }
        factory {
            UserDetailViewModel(
                get()
            )
        }
    }

    private val adapterModule = module {
        factory { UserListAdapter() }
    }
}