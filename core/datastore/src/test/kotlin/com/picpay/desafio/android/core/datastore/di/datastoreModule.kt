package com.picpay.desafio.android.core.datastore.di

import android.content.Context
import com.picpay.desafio.android.core.datastore.DataStoreRepository
import com.picpay.desafio.android.core.datastore.DesafioAppDataStoreRepository
import com.picpay.desafio.android.core.datastore.PreferencesDataSource
import org.junit.Test
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.test.verify.verify

@OptIn(KoinExperimentalAPI::class)
class DataStoreModuleTest {

    @Test
    fun checkDataStoreModule() {
        dataStoreModule.verify(
            extraTypes = listOf(
                Context::class,
                DesafioAppDataStoreRepository::class,
                PreferencesDataSource::class,
                DataStoreRepository::class,
            )
        )
    }
}
