package com.picpay.desafio.android.di.datasource

import com.picpay.desafio.android.data.datasources.BaseDataSource
import com.picpay.desafio.android.data.datasources.UserDataSource
import com.picpay.desafio.android.data.datasources.UserDataSourceInterface
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.dsl.module

@ExperimentalCoroutinesApi
val dataSourceModule = module {
    single { BaseDataSource }
    single<UserDataSourceInterface> {
        UserDataSource(service = get())
    }
}