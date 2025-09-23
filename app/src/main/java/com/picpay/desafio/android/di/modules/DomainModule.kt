package com.picpay.desafio.android.di.modules

import com.picpay.desafio.android.domain.usecase.GetPostsUseCase
import com.picpay.desafio.android.domain.usecase.GetUsersUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetUsersUseCase(get()) }
    factory { GetPostsUseCase(get()) }
}
