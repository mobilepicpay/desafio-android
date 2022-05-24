package com.picpay.desafio.android._config

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import org.junit.After
import org.junit.Before
import org.koin.core.context.loadKoinModules
import org.koin.core.context.stopKoin
import org.koin.core.module.Module
import org.koin.test.KoinTest

abstract class BaseKoinTest : KoinTest {

    val application: Application = ApplicationProvider.getApplicationContext()

    open val koinModules: List<Module> = emptyList()

    @Before
    open fun setupKoin() {
        loadKoinModules(koinModules)
    }

    @After
    open fun tearDownKoin() {
        stopKoin()
    }

}