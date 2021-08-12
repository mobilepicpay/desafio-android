package com.picpay.desafio.android.di

import com.picpay.desafio.android.data.db.datasources.UserLocalDataSource
import com.picpay.desafio.android.data.db.datasources.UserLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class LocalDataSourceModule {

    @Binds
    abstract fun bindUserLocalDataSource(
        userLocalDataSource: UserLocalDataSourceImpl
    ): UserLocalDataSource
}
