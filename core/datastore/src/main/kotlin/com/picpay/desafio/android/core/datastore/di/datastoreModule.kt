package com.picpay.desafio.android.core.datastore.di

import com.picpay.desafio.android.core.datastore.DataStoreRepository
import com.picpay.desafio.android.core.datastore.DesafioAppDataStoreRepository
import com.picpay.desafio.android.core.datastore.DesafioAppPreferencesDataSource
import com.picpay.desafio.android.core.datastore.PreferencesDataSource
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataStoreModule = module {

    singleOf(::DesafioAppPreferencesDataSource) { bind<PreferencesDataSource>() }

    singleOf(::DesafioAppDataStoreRepository) { bind<DataStoreRepository>() }
}
