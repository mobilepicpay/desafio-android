package com.picpay.desafio.contact_list.presentation.di

import com.picpay.desafio.contact_list.presentation.model.mapper.UserDomainToUserUiMapper
import com.picpay.desafio.contact_list.presentation.viewmodel.ContactUiState
import com.picpay.desafio.contact_list.presentation.viewmodel.ContactViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val contactListPresentationModule = module {
    factory { ContactUiState() }
    factory { UserDomainToUserUiMapper() }
    viewModel { ContactViewModel(get(), get(), get()) }
}