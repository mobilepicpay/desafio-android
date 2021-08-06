package com.picpay.desafio.android.di.repository

import com.picpay.desafio.android.data.repositories.UserRepository
import com.picpay.desafio.android.data.repositories.UserRepositoryInterface
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.dsl.module

@ExperimentalCoroutinesApi
val repositoryModule = module {
    single<UserRepositoryInterface> { UserRepository(dataSource = get()) }
}