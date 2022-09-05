package com.picpay.desafio.android.data.service

import com.google.gson.GsonBuilder
import com.picpay.desafio.android.data.services.PicPayService
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
class PicPayServiceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var service: PicPayService

    @Before
    fun createService() {
        val gson = GsonBuilder().create()

        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(PicPayService::class.java)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun `should correct endpoint when receiving parameter`() {
        runBlocking {
            mockWebServer.enqueue(MockResponse().setBody("[]"))
            val firstResult = service.getUsers()
            val firstRequest = mockWebServer.takeRequest()

            assertEquals(firstRequest.path, "/users")
        }
    }

}