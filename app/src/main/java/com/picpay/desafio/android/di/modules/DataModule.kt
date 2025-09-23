package com.picpay.desafio.android.di.modules

import com.picpay.desafio.android.data.repository.PostRepositoryImpl
import com.picpay.desafio.android.data.repository.UserRepositoryImpl
import com.picpay.desafio.android.domain.repository.PostRepository
import com.picpay.desafio.android.domain.repository.UserRepository
import org.koin.dsl.module

val dataModule = module {
    single<UserRepository> { UserRepositoryImpl(get()) }
    single<PostRepository> { PostRepositoryImpl(get()) }
}
