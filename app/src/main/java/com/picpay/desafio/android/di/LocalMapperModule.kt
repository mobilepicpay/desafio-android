package com.picpay.desafio.android.di

import com.picpay.desafio.android.data.db.entities.UserEntity
import com.picpay.desafio.android.data.db.mappers.LocalMapper
import com.picpay.desafio.android.data.db.mappers.UserLocalMapper
import com.picpay.desafio.android.models.User
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class LocalMapperModule {

    @Binds
    abstract fun bindUserLocalMapper(
        userLocalMapper: UserLocalMapper
    ): LocalMapper<UserEntity, User>
}
