package com.desafio.picpay.android.testcoreutil

import com.desafio.picpay.android.testcoreutil.ResourceFileReader.read
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class MockServerRule : TestWatcher() {
    private lateinit var mockWebServer: MockWebServer

    private val responses = mutableMapOf<RequestPath, Response>()

    override fun starting(description: Description?) {

        mockWebServer = MockWebServer()
        mockWebServer.start(MockServer.PORT)
        setupMockResponses()
    }

    override fun finished(description: Description?) {
        mockWebServer.shutdown()
    }

    private fun setupMockResponses() {
   
        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                responses[request.path]?.let { response ->
                    return MockResponse()
                        .setResponseCode(response.httpCode)
                        .setBody(read(response.jsonFile))
                }

                throw InvalidPathException(request.path, responses.map { it.key })
            }
        }
    }

    fun mockResponse(requestPath: String, jsonFile: String, responseCode: Int = 200) {
        responses[requestPath] = Response(jsonFile, responseCode)
    }

}

internal typealias RequestPath = String

internal class Response(val jsonFile: String, val httpCode: Int)

internal class InvalidPathException(requestPath: String?, mockedPaths: List<RequestPath>) :
    Exception(
        "Invalid request path!" +
                "\nRequest path:" +
                "\n - $requestPath" +
                "\nMocked paths:" +
                "\n - ${mockedPaths.joinToString("\n")}"
    )