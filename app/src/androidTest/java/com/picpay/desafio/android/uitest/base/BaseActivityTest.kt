package com.picpay.desafio.android.uitest.base

import androidx.test.platform.app.InstrumentationRegistry
import com.picpay.desafio.android.uitest.utils.GenericIdlingResource
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before

open class BaseActivityTest {


    protected val context = InstrumentationRegistry.getInstrumentation().targetContext!!
    protected var idlingResource: GenericIdlingResource? = null

    private var mockResponseByPath = HashMap<String, MockResponse>()

    private var server = MockWebServer().apply {
        dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return mockResponseByPath[request.path] ?: MockResponse()
            }
        }
    }

    @Before
    @Throws(Exception::class)
    fun setUp() {
        mockResponseByPath.clear()
        server.start(serverPort)
        idlingResource = null
    }

    @After
    fun tearDown() {
        server.close()
        idlingResource?.unregister()
    }

    protected fun setMockSuccessResponse(path: String, body: String) {
        setMockResponse(
            path,
            MockResponse()
                .setResponseCode(200)
                .setBody(body)
        )
    }

    protected fun setMockErrorResponse(path: String, status: Int, body: String? = null) {
        var response = MockResponse()
            .setResponseCode(status)
        body?.let {
            response.setBody(it)
        }
        setMockResponse(
            path,
            response
        )
    }

    private fun setMockResponse(path: String, response: MockResponse) {
        mockResponseByPath[path] = response
    }

    companion object {
        private const val serverPort = 8080
    }
}