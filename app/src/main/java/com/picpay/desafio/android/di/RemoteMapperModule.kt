package com.picpay.desafio.android.di

import com.picpay.desafio.android.data.api.mappers.RemoteMapper
import com.picpay.desafio.android.data.api.mappers.UserRemoteMapper
import com.picpay.desafio.android.data.api.responses.UserResponse
import com.picpay.desafio.android.models.User
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RemoteMapperModule {

    @Binds
    abstract fun bindUserRemoteMapper(
        userRemoteMapper: UserRemoteMapper
    ): RemoteMapper<UserResponse, User>
}
