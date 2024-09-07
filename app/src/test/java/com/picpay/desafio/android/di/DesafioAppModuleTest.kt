package com.picpay.desafio.android.di

import android.content.Context
import androidx.work.WorkerParameters
import org.junit.Test
import org.koin.androidx.workmanager.factory.KoinWorkerFactory
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.test.verify.verify

@OptIn(KoinExperimentalAPI::class)
class DesafioAppModuleTest {

    @Test
    fun checkKoinModules() {
        desafioAppModule.verify(
            extraTypes = listOf(
                Context::class,
                WorkerParameters::class,
                KoinWorkerFactory::class
            )
        )
    }
}