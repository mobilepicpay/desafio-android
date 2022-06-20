package com.picpay.desafio.feature.contactlist.datasource.internal.di

import com.picpay.desafio.feature.contactlist.datasource.internal.ContactListDatabase
import com.picpay.desafio.feature.contactlist.datasource.internal.UserDao
import com.picpay.desafio.feature.contactlist.datasource.internal.UserEntityMapperWithDomain
import com.picpay.desafio.feature.contactlist.datasource.internal.UserInternalDataSourceImpl
import com.picpay.desafio.feature.contactlist.repository.UserInternalDataSource
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val internalDatasourceModule = module {
    single {
        ContactListDatabase.createDatabase(androidApplication())
    }

    factory {
        provideUserDao(get())
    }

    factory {
        UserEntityMapperWithDomain()
    }

    factory<UserInternalDataSource> {
        UserInternalDataSourceImpl(get(), get())
    }
}

private fun provideUserDao(database: ContactListDatabase): UserDao {
    return database.userDao()
}