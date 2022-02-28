package com.picpay.desafio.android.core.di

import com.picpay.desafio.android.data.api.NetworkModule
import com.picpay.desafio.android.data.repository.*
import com.picpay.desafio.android.domain.repositories.UsersRepository
import com.picpay.desafio.android.domain.usecases.GetRemoteUsersUseCase
import com.picpay.desafio.android.presentation.viewmodels.UserListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel

import org.koin.dsl.module

object AppModule {

    val mRepositoryModules = module {
        single<UsersRemoteDatasource> { UsersRemoteDatasourceImpl(service = get()) }
        single<UsersLocalDatasource> { UsersLocalDatasourceImpl() }
        single<UsersRepository> { UsersRepositoryImpl(remoteDatasource = get(), localDatasource = get()) }
    }

    val mUseCaseModules = module {
        factory { GetRemoteUsersUseCase(repository = get()) }
    }

    val mNetworkModules = module {
        single { NetworkModule().createPicPayApi() }
    }

    val mViewModels = module {
        viewModel {
            UserListViewModel(getUsersRemote = get())
        }
    }

}