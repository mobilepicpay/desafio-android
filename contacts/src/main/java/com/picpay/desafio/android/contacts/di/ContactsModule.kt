package com.picpay.desafio.android.contacts.di

import com.picpay.desafio.android.commons.mapper.Mapper
import com.picpay.desafio.android.contacts.data.model.ContactResponse
import com.picpay.desafio.android.contacts.data.network.ContactsService
import com.picpay.desafio.android.contacts.data.repository.ContactsRepository
import com.picpay.desafio.android.contacts.data.repository.ContactsRepositoryImpl
import com.picpay.desafio.android.contacts.domain.mapper.ContactResponseMapper
import com.picpay.desafio.android.contacts.domain.model.Contact
import com.picpay.desafio.android.contacts.domain.usecase.GetContacts
import com.picpay.desafio.android.contacts.domain.usecase.GetContactsImpl
import com.picpay.desafio.android.contacts.presentation.viewmodel.ContactsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val contactsModule = module {
    viewModel { ContactsViewModel(get(), get()) }
    single {
        val retrofit = get<Retrofit>()
        retrofit.create(ContactsService::class.java)
    }

    single<ContactsRepository> {
        ContactsRepositoryImpl(get())
    }

    single<GetContacts> {
        GetContactsImpl(get(), get())
    }

    single<Mapper<ContactResponse, Contact>> { ContactResponseMapper() }
}