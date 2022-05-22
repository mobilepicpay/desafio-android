package com.picpay.desafio.android._config

import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.koin.core.component.inject
import org.koin.core.context.loadKoinModules
import org.koin.core.context.stopKoin
import org.koin.core.module.Module

open class DataTest : BaseKoinTest() {

    protected val mockWebServer: MockWebServer by inject()

    open val koinModules: List<Module> = emptyList()

    @Before
    open fun setup() {
        loadKoinModules(koinModules)
        try {
            mockWebServer.start()
        } catch (e: Exception) {
        }
    }

    @After
    open fun tearDown() {
        stopKoin()
        try {
            mockWebServer.start()
        } catch (e: Exception) {
        }
    }
}