package com.picpay.desafio.android.core.domain.di

import com.picpay.desafio.android.core.domain.usecase.LocalUsersFlowUseCase
import com.picpay.desafio.android.core.domain.usecase.SearchLocalUsersFlowUseCase
import com.picpay.desafio.android.core.domain.usecase.test.FakeLocalUsersFlowUseCase
import com.picpay.desafio.android.core.domain.usecase.test.FakeSearchLocalUsersFlowUseCase
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val testingDomainModule = module {

    singleOf(::FakeLocalUsersFlowUseCase) { bind<LocalUsersFlowUseCase>() }

    singleOf(::FakeSearchLocalUsersFlowUseCase) { bind<SearchLocalUsersFlowUseCase>() }

}