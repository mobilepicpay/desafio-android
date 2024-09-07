package com.picpay.desafio.android.di

import com.picpay.desafio.android.core.data.di.dataModule
import com.picpay.desafio.android.core.database.di.databaseModule
import com.picpay.desafio.android.core.datastore.di.dataStoreModule
import com.picpay.desafio.android.core.domain.di.domainModule
import com.picpay.desafio.android.feature.contacts.di.featureContactsModule
import com.picpay.desafio.android.core.network.di.networkModule
import com.picpay.desafio.android.sync.work.di.syncModule
import org.koin.dsl.module

val desafioAppModule = module {
    includes(
        syncModule,
        networkModule,
        dataModule,
        domainModule,
        dataStoreModule,
        databaseModule,
        featureContactsModule
    )
}