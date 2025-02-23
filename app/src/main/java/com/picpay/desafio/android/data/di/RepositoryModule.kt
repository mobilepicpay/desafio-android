package com.picpay.desafio.android.data.di

import com.picpay.desafio.android.data.features.contacts.repository.ContactsRepositoryImpl
import com.picpay.desafio.android.domain.features.contacts.repository.ContactsRepository
import org.koin.dsl.module

object RepositoryModule {
    fun getModule() = repository

    private val repository = module {
        single<ContactsRepository> {
            ContactsRepositoryImpl(
                service = get(),
                dao = get()
            )
        }
    }
}
