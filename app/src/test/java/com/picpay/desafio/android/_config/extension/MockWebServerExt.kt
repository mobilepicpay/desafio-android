package com.picpay.desafio.android._config.extension

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer

fun MockWebServer.enqueueResponse(body: String, code: Int) {
    val response = MockResponse().apply {
        setBody(body)
        setResponseCode(code)
    }
    enqueue(response)
}