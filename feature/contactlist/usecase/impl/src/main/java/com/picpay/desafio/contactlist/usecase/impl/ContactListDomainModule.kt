package com.picpay.desafio.contactlist.usecase.impl

import com.picpay.desafio.feature.contactlist.usecase.GetUsersUseCase
import org.koin.dsl.module

val contactListDomainModule = module {
    factory<GetUsersUseCase> { GetUsersUseCaseImpl(get()) }
}