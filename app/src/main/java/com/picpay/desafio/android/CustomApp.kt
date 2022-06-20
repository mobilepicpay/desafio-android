package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.contact_list.presentation.di.contactListPresentationModule
import com.picpay.desafio.contactlist.datasource.remote.impl.di.contactListDatasourceModule
import com.picpay.desafio.contactlist.usecase.impl.contactListDomainModule
import com.picpay.desafio.feature.contactlist.datasource.internal.di.internalDatasourceModule
import com.picpay.desafio.network.di.networkModule
import com.picpay.desafio.feature.contactlist.repository.impl.di.contactListRepositoryModule
import com.picpay.desafio.ui.theme.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CustomApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CustomApp)
            modules(
                networkModule,
                contactListRepositoryModule,
                contactListDomainModule,
                contactListPresentationModule,
                contactListDatasourceModule,
                internalDatasourceModule,
                uiModule
            )
        }
    }

}