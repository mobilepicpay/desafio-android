package com.picpay.desafio.android.core.datastore.di.test


import android.system.Os.bind
import com.picpay.desafio.android.core.datastore.DataStoreRepository
import com.picpay.desafio.android.core.datastore.DesafioAppDataStoreRepository
import com.picpay.desafio.android.core.datastore.DesafioAppPreferencesDataSource
import com.picpay.desafio.android.core.datastore.PreferencesDataSource
import com.picpay.desafio.android.core.datastore.test.FakePreferencesDataSource
import com.picpay.desafio.android.core.datastore.test.FakePreferencesDataStore
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val testingDatastoreModule = module {

    singleOf(::FakePreferencesDataSource) { bind<PreferencesDataSource>() }

    singleOf(::FakePreferencesDataStore) { bind<DataStoreRepository>() }
}
