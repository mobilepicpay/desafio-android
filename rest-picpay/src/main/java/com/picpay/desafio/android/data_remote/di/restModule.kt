package com.picpay.desafio.android.data_remote.di

import com.picpay.desafio.android.data_remote.api.PicPayUserService
import com.picpay.desafio.android.data_remote.repository.UserRepositoryImpl
import com.picpay.desafio.android.domain.repository.UserRepository
import com.picpay.desafio.android.networking.NetworkBuilder
import com.picpay.desafio.android.networking.OkhttpBuilder
import org.koin.dsl.module

val restPicpayModule by lazy {
    module {
        single {
            NetworkBuilder<PicPayUserService>(
                okHttpClient = OkhttpBuilder(),
                url = PicPayUserService.API_URL
            )
        }

        single<UserRepository> { UserRepositoryImpl(rest = get()) }
    }
}