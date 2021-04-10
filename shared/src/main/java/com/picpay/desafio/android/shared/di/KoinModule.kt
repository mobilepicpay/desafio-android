package com.picpay.desafio.android.shared.di

import org.koin.core.KoinApplication

abstract class KoinModule {
    abstract fun loadModule(koinApplication: KoinApplication)
}