package com.picpay.desafio.android.di

import com.picpay.desafio.android.data.api.webservices.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object WebservicesModule {

    @Provides
    fun provideUserService(
        networkClient: Retrofit
    ): UserService {
        return networkClient.create(UserService::class.java)
    }
}
