package com.picpay.desafio.android._config

import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.koin.core.component.inject

open class DataTest : BaseKoinTest() {

    protected val mockWebServer: MockWebServer by inject()

    @Before
    open fun setupMockWebServer() {
        try {
            mockWebServer.start()
        } catch (_: Exception) {
        }
    }

    @After
    open fun tearDownMockWebServer() {
        try {
            mockWebServer.start()
        } catch (_: Exception) {
        }
    }

}