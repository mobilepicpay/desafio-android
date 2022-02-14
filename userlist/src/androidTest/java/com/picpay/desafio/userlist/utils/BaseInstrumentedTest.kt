package com.picpay.desafio.userlist.utils

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockWebServer
import org.junit.After


abstract class BaseInstrumentedTest {

    val mockWebServer: MockWebServer = MockWebServer()

    init {
        mockWebServer.start(8080)
    }

    @After
    fun after() {
        mockWebServer.shutdown()

    }

    fun setDispatcher(it: Dispatcher){
        mockWebServer.dispatcher = it
    }
}