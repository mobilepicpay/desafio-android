package com.picpay.desafio.android.core.di

import androidx.room.Room
import com.picpay.desafio.android.data.api.NetworkModule
import com.picpay.desafio.android.data.local.AppDatabase
import com.picpay.desafio.android.data.repository.*
import com.picpay.desafio.android.domain.repositories.UsersRepository
import com.picpay.desafio.android.domain.usecases.GetLocalUsersUseCase
import com.picpay.desafio.android.domain.usecases.GetRemoteUsersUseCase
import com.picpay.desafio.android.presentation.viewmodels.UserListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel

import org.koin.dsl.module

object AppModule {

    val repositoryModules = module {
        single<UsersRemoteDatasource> { UsersRemoteDatasourceImpl(service = get()) }
        single<UsersLocalDatasource> { UsersLocalDatasourceImpl(db = get()) }
        single<UsersRepository> { UsersRepositoryImpl(remoteDatasource = get(), localDatasource = get()) }
    }

    val useCaseModules = module {
        factory { GetRemoteUsersUseCase(repository = get()) }
        factory { GetLocalUsersUseCase(repository = get()) }
    }

    val networkModules = module {
        single { NetworkModule().createPicPayApi() }
    }

    val dbModule = module {
        single { Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "desafioandroid-database")
            .build()
        }
        single { get<AppDatabase>().userDao() }
    }

    val viewModels = module {
        viewModel {
            UserListViewModel(getUsersRemote = get(), getUsersLocal = get())
        }
    }

}