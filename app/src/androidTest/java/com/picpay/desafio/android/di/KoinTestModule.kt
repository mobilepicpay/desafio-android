package com.picpay.desafio.android.di

import com.picpay.desafio.android.data.repository.UserRepository
import io.mockk.mockk
import org.koin.dsl.module

fun getTestModule() = module {

    single<UserRepository>(override = true) { mockk() }

}