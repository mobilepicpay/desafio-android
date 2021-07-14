package com.picpay.desafio.android.contacts.di

import com.picpay.desafio.android.contacts.ContactsInteractor
import com.picpay.desafio.android.contacts.ContactsViewModel
import com.picpay.desafio.android.contacts.adapter.UserListAdapter
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @author Vitor Herrmann on 12/07/2021
 */
object ContactsModule {

    val module = module {
        factory { ContactsInteractor(get(), get(), get()) }
        factory { UserListAdapter() }
        viewModel { ContactsViewModel(get()) }
    }
}
