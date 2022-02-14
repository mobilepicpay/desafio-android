package com.picpay.desafio.userlist.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.picpay.desafio.common.extensions.resolveRetrofit
import com.picpay.desafio.userlist.data.dao.UserDatabase
import com.picpay.desafio.userlist.data.dao.UserListDao
import com.picpay.desafio.userlist.data.respository.UserListRepositoryImpl
import com.picpay.desafio.userlist.data.service.PicPayService
import com.picpay.desafio.userlist.data.service.PicPayServiceMock
import com.picpay.desafio.userlist.domain.repository.UserListRepository
import com.picpay.desafio.userlist.domain.usecase.GetUserListUseCase
import com.picpay.desafio.userlist.domain.usecase.GetUserListUseCaseImpl
import com.picpay.desafio.userlist.presentation.viewmodel.UserListViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object UserListModule {

    val modules = module{

        fun provideDatabase(application: Context): UserDatabase {
            return Room.databaseBuilder(application, UserDatabase::class.java, UserDatabase.USER_TABLE_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }

        fun provideDao(database: UserDatabase): UserListDao {
            return  database.dao
        }

        single { provideDatabase(androidContext()) }
        single { provideDao(get()) }

        single<PicPayService>{ resolveRetrofit() ?: PicPayServiceMock() }

        factory<UserListRepository> { UserListRepositoryImpl(get(),get()) }

        factory<GetUserListUseCase> { GetUserListUseCaseImpl(get()) }

        viewModel { UserListViewModel(get()) }
    }
}