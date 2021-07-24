package com.picpay.desafio.android.di

import com.picpay.desafio.android.data.PicPayRepository
import com.picpay.desafio.android.data.PicPayRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun provideRepository(picPayRepositoryImpl: PicPayRepositoryImpl):
            PicPayRepository

}