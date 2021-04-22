package com.picpay.desafio.android.feature_contacts.di

import com.picpay.desafio.android.domain.usescases.FetchUsersUseCase
import com.picpay.desafio.android.feature_contacts.presentation.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureContactModule by lazy {
    module {
        viewModel {
            val usecase = FetchUsersUseCase(
                userRepositoryRemote = get(),
                userCacheRepository = get()
            )
            UserViewModel(usecase = usecase)
        }
    }
}