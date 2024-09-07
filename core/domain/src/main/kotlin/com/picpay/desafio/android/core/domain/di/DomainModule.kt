package com.picpay.desafio.android.core.domain.di

import com.picpay.desafio.android.core.domain.usecase.LocalUsersFlowUseCase
import com.picpay.desafio.android.core.domain.usecase.LocalUsersFlowUseCaseImpl
import com.picpay.desafio.android.core.domain.usecase.SearchLocalUsersFlowUseCase
import com.picpay.desafio.android.core.domain.usecase.SearchLocalUsersFlowUseCaseImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val domainModule = module {

    singleOf(::LocalUsersFlowUseCaseImpl) { bind<LocalUsersFlowUseCase>() }
    singleOf(::SearchLocalUsersFlowUseCaseImpl) { bind<SearchLocalUsersFlowUseCase>() }

}