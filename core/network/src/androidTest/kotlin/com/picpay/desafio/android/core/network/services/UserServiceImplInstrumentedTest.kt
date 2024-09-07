package com.picpay.desafio.android.core.network.services

import com.picpay.desafio.android.core.network.NetworkClient
import com.picpay.desafio.android.core.network.TIMEOUT_IN_SECONDS
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.net.ConnectException
import java.util.concurrent.TimeUnit

class UserServiceImplInstrumentedTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var userService: UserServiceImpl
    private lateinit var okHttpClient: OkHttpClient

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        okHttpClient = NetworkClient.okHttpClientBuilder()

        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        userService = retrofit.create(UserServiceImpl::class.java)
    }

    @After
    fun tearDown() {

        runCatching { mockWebServer.shutdown() }
            .onFailure { it.printStackTrace() }

    }

    @Test
    fun getUsers_returns_successful_response() = runTest {
        // Given
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody("""[{"img":"img_url","name":"User1","id":1,"username":"user1"}]""")
        mockWebServer.enqueue(mockResponse)

        // When
        val response = userService.getUsers()

        // Then
        assertEquals(true, response.isSuccessful)
        assertEquals(1, response.body()?.size)
        assertEquals("User1", response.body()?.get(0)?.name)
    }

    @Test
    fun getUsers_returns_empty_list_on_empty_response() = runTest {
        // Given
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody("[]")
        mockWebServer.enqueue(mockResponse)

        // When
        val response = userService.getUsers()

        // Then
        assertEquals(true, response.isSuccessful)
        assertEquals(0, response.body()?.size)
    }

    @Test
    fun getUsers_handles_error_response() = runTest {
        // Given
        val mockResponse = MockResponse()
            .setResponseCode(500)
        mockWebServer.enqueue(mockResponse)

        // When
        val response = userService.getUsers()

        // Then
        assertEquals(false, response.isSuccessful)
        assertEquals(500, response.code())
    }

    @Test
    fun getUsers_handles_malformed_json() = runTest {
        // Given
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody("""[{"img":"img_url","name":"User1","id":1,"username":"user1"""") // Missing closing }
        mockWebServer.enqueue(mockResponse)

        // When
        val result = runCatching { userService.getUsers() }

        // Then
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is IOException)
    }

    @Test
    fun getUsers_network_failure() = runTest {
        // Given
        mockWebServer.shutdown()

        // When
        val result = runCatching { userService.getUsers() }

        // Then
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is ConnectException)
    }

    @Test
    fun getUsers_returns_timeout() = runTest {
        // Given
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody("""[{"img":"img_url","name":"User1","id":1,"username":"user1"}]""")
            .setBodyDelay(TIMEOUT_IN_SECONDS * 2, TimeUnit.SECONDS) // Simulate delay
        mockWebServer.enqueue(mockResponse)

        // When
        val result = runCatching { userService.getUsers() }

        // Then
        assertTrue(result.isFailure) // Verify that an exception was thrown
        assertTrue(result.exceptionOrNull() is IOException)
    }
}