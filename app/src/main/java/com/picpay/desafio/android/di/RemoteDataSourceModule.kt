package com.picpay.desafio.android.di

import com.picpay.desafio.android.data.api.datasources.UserRemoteDataSource
import com.picpay.desafio.android.data.api.datasources.UserRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RemoteDataSourceModule {

    @Binds
    abstract fun bindUserRemoteDataSource(
        userRemoteDataSource: UserRemoteDataSourceImpl
    ): UserRemoteDataSource
}
