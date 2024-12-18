package com.picpay.desafio.android.ui.di

import com.picpay.desafio.android.data.coroutine.DispatcherProvider
import com.picpay.desafio.android.data.coroutine.DispatcherProviderImpl
import com.picpay.desafio.android.ui.contacts.ContactsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {

    viewModelOf(::ContactsViewModel)

    single<DispatcherProvider> { DispatcherProviderImpl() }
}
