package com.picpay.desafio.ui.theme.di

import com.picpay.desafio.ui.theme.resourceprovider.StringResourceProvider
import com.picpay.desafio.ui.theme.resourceprovider.StringResourceProviderImpl
import org.koin.dsl.module

val uiModule = module {
    factory<StringResourceProvider> {
        StringResourceProviderImpl(get())
    }
}