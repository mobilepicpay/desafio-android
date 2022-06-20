package com.picpay.desafio.contactlist.datasource.remote.impl.di

import com.picpay.desafio.contactlist.datasource.remote.impl.UserRemoteDataSourceImpl
import com.picpay.desafio.contactlist.datasource.remote.impl.UserResponseToUserDomainMapper
import com.picpay.desafio.contactlist.datasource.remote.impl.UserService
import com.picpay.desafio.feature.contactlist.repository.UserRemoteDataSource
import org.koin.dsl.module
import retrofit2.Retrofit

val contactListDatasourceModule = module {
    factory { get<Retrofit>().create(UserService::class.java) }
    factory { UserResponseToUserDomainMapper() }
    factory<UserRemoteDataSource> { UserRemoteDataSourceImpl(get(), get()) }
}