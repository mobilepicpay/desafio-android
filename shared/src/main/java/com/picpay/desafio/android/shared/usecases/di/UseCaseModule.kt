package com.picpay.desafio.android.shared.usecases.di

import com.picpay.desafio.android.shared.usecases.*
import org.koin.dsl.module

/**
 * @author Vitor Herrmann on 12/07/2021
 */
object UseCaseModule {

    val module = module {
        factory<FetchContactsUseCase> { FetchContactsUseCaseImpl(get()) }
        factory<SaveContactsUseCase> { SaveContactsUseCaseImpl(get()) }
        factory<GetContactsUseCase> { GetContactsUseCaseImpl(get()) }
    }
}
