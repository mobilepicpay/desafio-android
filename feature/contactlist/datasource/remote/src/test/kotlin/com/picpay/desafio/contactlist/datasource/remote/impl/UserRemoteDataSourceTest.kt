package com.picpay.desafio.contactlist.datasource.remote.impl

import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserRemoteDataSourceTest {

    private lateinit var mockWebServer: MockWebServer

    private lateinit var api: UserService

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        api = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserService::class.java)

    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun getUser_sentRequest_getExpectedResult() = runTest {
        enqueueMockRespose("get_users_list.json")

        val expected = listOfUserResponseExpected()
        val actual = api.getUsers()

        Truth.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun getSearchedResult_sentRequest_verifyPathAndMethodIsExpected() {
        runBlocking {

            enqueueMockRespose("get_users_empty.json")

            val responseBody = api.getUsers()

            val request = mockWebServer.takeRequest()
            Truth.assertThat(responseBody).isNotNull()
            Truth.assertThat(request.path).isEqualTo("/users")
            Truth.assertThat(request.method).isEqualTo("GET")
        }
    }

    @Test
    fun getUser_sentRequest_getEmptyList() = runTest {
        enqueueMockRespose("get_users_empty.json")

        val expected = listOf<UserResponse>()
        val actual = api.getUsers()

        Truth.assertThat(actual).isEqualTo(expected)
    }

    @Test()
    fun getUser_sentRequest_getNotFoundError() = runTest {

        mockWebServer.enqueue(MockResponse().setResponseCode(404))

        val actual = kotlin.runCatching {
            api.getUsers()
        }.onFailure {
            Truth.assertThat(it).isInstanceOf(HttpException::class.java)
        }

        Truth.assertThat(actual.isFailure).isTrue()
        Truth.assertThat(actual.exceptionOrNull()?.message)
            .isEqualTo("HTTP 404 Client Error")
    }

    private fun listOfUserResponseExpected() = listOf(
        UserResponse(
            img = "https://randomuser.me/api/portraits/men/1.jpg",
            name = "Sandrine Spinka",
            id = 1,
            username = "Tod86"
        ),
        UserResponse(
            img = "https://randomuser.me/api/portraits/men/2.jpg",
            name = "Carli Carroll",
            id = 2,
            username = "Constantin_Sawayn"
        ),
        UserResponse(
            img = "https://randomuser.me/api/portraits/men/3.jpg",
            name = "Annabelle Reilly",
            id = 3,
            username = "Lawrence_Nader62"
        ),
        UserResponse(
            img = "https://randomuser.me/api/portraits/men/4.jpg",
            name = "Mrs. Hilton Welch",
            id = 4,
            username = "Tatyana_Ullrich"
        )
    )


    private fun enqueueMockRespose(fileName: String) {
        javaClass.classLoader?.let {
            val inputStream = it.getResourceAsStream(fileName)
            val source = inputStream.source().buffer()
            val mockResponse = MockResponse()
            mockResponse.setBody(source.readString(Charsets.UTF_8))
            mockWebServer.enqueue(mockResponse)
        }
    }
}