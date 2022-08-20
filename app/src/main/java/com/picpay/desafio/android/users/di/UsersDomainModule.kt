package com.picpay.desafio.android.users.di

import com.picpay.desafio.android.users.domain.usecase.GetUsers
import com.picpay.desafio.android.users.domain.usecase.GetUsersUseCase
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UsersDomainModule {

    @Binds
    @Reusable
    fun bindsGetUsersUseCase(
        usecase: GetUsers
    ): GetUsersUseCase
}