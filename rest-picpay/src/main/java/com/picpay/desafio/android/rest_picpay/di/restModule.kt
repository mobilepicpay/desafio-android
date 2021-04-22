package com.picpay.desafio.android.rest_picpay.di

import com.picpay.desafio.android.rest_picpay.api.PicPayUserService
import com.picpay.desafio.android.rest_picpay.repository.UserRepositoryImpl
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