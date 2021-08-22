package com.picpay.desafio.android.local

import com.picpay.desafio.android.local.config.DataBase
import org.koin.core.module.Module
import org.koin.dsl.module

class LocalDI {

    fun get() : Module {
        return module {
            single { DataBase.invoke(context = get()) }
        }
    }
}