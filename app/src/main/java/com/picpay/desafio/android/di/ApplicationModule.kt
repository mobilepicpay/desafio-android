package com.picpay.desafio.android.di

import com.picpay.desafio.android.contacts.di.ContactsModule
import com.picpay.desafio.android.shared.database.di.DatabaseModule
import com.picpay.desafio.android.shared.services.di.ServicesModule
import com.picpay.desafio.android.shared.usecases.di.UseCaseModule

/**
 * @author Vitor Herrmann on 12/07/2021
 */
object ApplicationModule {

    val modules = mutableListOf(
        DatabaseModule.module,
        NetworkModule.module,
        ServicesModule.module,
        UseCaseModule.module,
        ContactsModule.module
    )
}
