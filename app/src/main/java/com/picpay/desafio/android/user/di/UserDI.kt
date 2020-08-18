package com.picpay.desafio.android.user.di

import androidx.room.Room
import br.com.ribeiro.network.WebClient
import com.picpay.desafio.android.user.database.UserDataBase
import com.picpay.desafio.android.user.database.constants.DataBaseConstants
import com.picpay.desafio.android.user.repository.UserRepository
import com.picpay.desafio.android.user.repository.UserRepositoryImplement
import com.picpay.desafio.android.user.service.UserService
import com.picpay.desafio.android.user.service.constants.ServiceConstant
import com.picpay.desafio.android.user.usecase.UserUseCase
import com.picpay.desafio.android.user.usecase.UserUseCaseImplement
import com.picpay.desafio.android.user.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object UserDI {
    private val viewModelModule = module {
        viewModel { UserViewModel(useCase = get()) }
        factory { UserUseCaseImplement(repository = get()) as UserUseCase }
        factory { UserRepositoryImplement(service = get(), dataBase = get()) as UserRepository }
        single { WebClient.service<UserService>(ServiceConstant.URL) }
        single {
            Room.databaseBuilder(
                get(),
                UserDataBase::class.java,
                DataBaseConstants.DATABASE_NAME
            )
                .build()
        }
        single { get<UserDataBase>().userDAO() }
    }

    fun init() = loadKoinModules(viewModelModule)
}