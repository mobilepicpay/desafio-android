package com.picpay.desafio.android.feature.contacts.di

import com.picpay.desafio.android.core.data.repository.UserLocalDataSource
import com.picpay.desafio.android.core.data.repository.UserRemoteDataSource
import com.picpay.desafio.android.core.network.services.UserService
import com.picpay.desafio.android.feature.contacts.viewmodel.ContactsScreenViewModel
import com.picpay.desafio.android.core.network.services.UserServiceImpl
import com.picpay.desafio.android.feature.contacts.datasource.local.UserLocalDataSourceImpl
import com.picpay.desafio.android.feature.contacts.datasource.remote.UserRemoteDataSourceImpl
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit

val featureContactsModule = module {

    viewModelOf(::ContactsScreenViewModel)

    single<UserService> { get<Retrofit>().create(UserServiceImpl::class.java) }

    singleOf(::UserRemoteDataSourceImpl) { bind<UserRemoteDataSource>() }
    singleOf(::UserLocalDataSourceImpl) { bind<UserLocalDataSource>() }

}