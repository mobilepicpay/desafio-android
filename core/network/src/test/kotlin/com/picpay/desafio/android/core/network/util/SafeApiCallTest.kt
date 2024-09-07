package com.picpay.desafio.android.core.network.util

import com.picpay.desafio.android.core.model.ApiResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import java.io.IOException
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SafeApiCallTest {

    private lateinit var dispatcher: CoroutineDispatcher

    @Before
    fun setUp() {
        dispatcher = StandardTestDispatcher()
    }

    @Test
    fun `safeApiCall returns Success when response is successful and body is non-null`() = runTest(dispatcher) {
        // Given
        val mockResponse = Response.success("Success")
        val apiCall: suspend () -> Response<String> = { mockResponse }

        // When
        val result = safeApiCall(dispatcher, apiCall)

        // Then
        assertEquals(ApiResponse.Success("Success"), result)
    }

    @Test
    fun `safeApiCall returns Error when response is not successful`() = runTest(dispatcher) {
        // Given
        val errorMessage = "HTTP 404 Not Found"
        val mockResponse = Response.error<String>(404, errorMessage.toResponseBody())
        val apiCall: suspend () -> Response<String> = { mockResponse }

        // When
        val result = safeApiCall(dispatcher, apiCall)

        // Then
        assertTrue(result is ApiResponse.Error, "Expected ApiResponse.Error but got ${result::class.simpleName}")

        val actualMessage = result.error?.message

        assertEquals(404, result.code)
        assertTrue(actualMessage != null, "Error message should not be null")
        assertTrue(actualMessage.contains("HTTP 404"), "Expected error message to contain 'HTTP 404', but got '$actualMessage'")
    }


    @Test
    fun `safeApiCall returns Error when exception is thrown`() = runTest(dispatcher) {
        // Given
        val apiCall: suspend () -> Response<String> = { throw IOException("Network Error") }

        // When
        val result = com.picpay.desafio.android.core.network.util.safeApiCall(dispatcher, apiCall)

        // Then
        assertTrue(result is ApiResponse.Error)
        assertEquals("Network Error", (result).error?.message)
    }
}