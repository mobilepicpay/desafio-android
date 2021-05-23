package com.picpay.desafio.android.di

import androidx.room.Room
import com.picpay.desafio.android.data.api.HttpClient
import com.picpay.desafio.android.data.db.DataBaseProvider
import com.picpay.desafio.android.data.local.UserLocalDataSource
import com.picpay.desafio.android.data.local.UserLocalDataSourceImp
import com.picpay.desafio.android.data.mappers.DbUserToUserMapper
import com.picpay.desafio.android.data.mappers.UserResponseMapper
import com.picpay.desafio.android.data.mappers.UserToDbUserMapper
import com.picpay.desafio.android.data.remote.UserRemoteDataSource
import com.picpay.desafio.android.data.remote.UserRemoteDataSourceImp
import com.picpay.desafio.android.data.repository.UserRepository
import com.picpay.desafio.android.data.repository.UserRepositoryImp
import com.picpay.desafio.android.ui.viewmodel.UserViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

class KoinModule {

    fun getModule(): Module = module {
        viewModel { UserViewModel(repository = get()) }
        factory<UserRepository> {

            UserRepositoryImp(
                localDataSource = get(),
                remoteDataSource = get()
            )
        }
        factory<UserLocalDataSource> {
            UserLocalDataSourceImp(
                userDao = get(),
                dbUserToUserMapper = get(),
                userToDbUserMapper = get()

            )
        }

        factory<UserRemoteDataSource> {
            UserRemoteDataSourceImp(
                service = get<HttpClient>().getUserService(),
                userResponseMapper = get()
            )
        }

        factory { HttpClient() }
        factory { UserResponseMapper() }

        single {
            Room.databaseBuilder(
                androidContext(),
                DataBaseProvider::class.java,
                "user-database"
            ).fallbackToDestructiveMigration()
                .build()
        }

        factory { get<DataBaseProvider>().provideUserDao() }
        factory { DbUserToUserMapper() }
        factory { UserToDbUserMapper() }


    }

}