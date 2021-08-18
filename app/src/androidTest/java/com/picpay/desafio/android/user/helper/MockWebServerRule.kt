package com.picpay.desafio.android.user.helper

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class MockWebServerRule: TestWatcher() {

    private val mockWebServer = MockWebServer()

    private val userSuccessResponse = mockResponseWithJsonFile("json/user/user_response.json")

    override fun starting(description: Description?) {
        super.starting(description)
        mockWebServer.start(port = 8080)
        mockWebServer.dispatcher = getDefaultDispatcher()
    }

    override fun finished(description: Description?) {
        super.finished(description)
        mockWebServer.shutdown()
    }

    private fun mockResponseWithJsonFile(jsonFilePath: String, code: Int = 200): MockResponse {
        val json = javaClass
            .classLoader
            ?.getResourceAsStream(jsonFilePath)
            ?.bufferedReader()
            .use { it!!.readText() }

        return MockResponse().setBody(json).setResponseCode(code)
    }

    private fun getDefaultDispatcher(): Dispatcher {
        return object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                with(request.path!!) {
                    return when {
                        startsWith("/users") -> userSuccessResponse
                        else -> MockResponse().setResponseCode(404)
                    }
                }
            }
        }
    }
}