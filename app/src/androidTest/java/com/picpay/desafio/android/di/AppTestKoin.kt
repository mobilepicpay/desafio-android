package com.picpay.desafio.android.di

import com.picpay.desafio.android.espresso.IdlingResource
import com.picpay.desafio.android.shared.di.KoinModule
import org.koin.core.KoinApplication
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object AppTestKoin : KoinModule() {

    private val idlingResourceModule = module {
        factory(override = true) { IdlingResource.getCoroutineService() }
    }

    override fun loadModule(koinApplication: KoinApplication) {
        koinApplication.apply {
            loadKoinModules(idlingResourceModule)
        }
    }
}