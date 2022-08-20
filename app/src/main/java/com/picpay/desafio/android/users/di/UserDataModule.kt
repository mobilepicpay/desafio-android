package com.picpay.desafio.android.users.di

import com.picpay.desafio.android.users.data.datasource.UsersRemoteDataSource
import com.picpay.desafio.android.users.data.datasource.UsersServiceDataSource
import com.picpay.desafio.android.users.data.repository.UsersDefaultRepository
import com.picpay.desafio.android.users.data.repository.UsersRepository
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UserDataModule {

    @Binds
    @Reusable
    fun bindsUsersRemoteDataSource(
        serviceDataSource: UsersServiceDataSource
    ): UsersRemoteDataSource

    @Binds
    @Reusable
    fun bindsUsersRepository(
        defaultRepository: UsersDefaultRepository
    ): UsersRepository
}