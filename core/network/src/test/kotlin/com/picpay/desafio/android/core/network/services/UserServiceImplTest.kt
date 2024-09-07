package com.picpay.desafio.android.core.network.services

import com.picpay.desafio.android.core.network.model.UserResponse
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class UserServiceImplTest {

    private lateinit var userService: UserServiceImpl

    @Before
    fun setUp() {
        userService = mockk()
    }

    @Test
    fun `getUsers returns successful response`() = runBlocking {
        // Given
        val expectedUsers = listOf(
            UserResponse("img_url", "User1", 1, "user1"),
            UserResponse("img_url", "User2", 2, "user2")
        )
        coEvery { userService.getUsers() } returns Response.success(expectedUsers)

        // When
        val response = userService.getUsers()

        // Then
        assertEquals(true, response.isSuccessful)
        assertEquals(expectedUsers, response.body())
    }

    @Test
    fun `getUsers returns error response`() = runBlocking {
        // Given
        val responseBody = mockk<ResponseBody>()
        every { responseBody.contentType() } returns "application/json".toMediaType()
        every { responseBody.contentLength() } returns 0L
        every { responseBody.string() } returns "{}"
        coEvery { userService.getUsers() } returns Response.error(404, responseBody)

        // When
        val response = userService.getUsers()

        // Then
        assertEquals(false, response.isSuccessful)
        assertEquals(404, response.code())
    }
}