package com.picpay.desafio.android

import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Call
import retrofit2.Response

class ExampleServiceTest {

    private val api = mockk<PicPayService>()

    private val service = ExampleService(api)

    @Test
    fun exampleTest() {
        // given
        val call = mockk<Call<List<User>>>()
        val expectedUsers = emptyList<User>()

        every { call.execute() } returns Response.success(expectedUsers)
        every { api.getUsers() } returns call

        // when
        val users = service.example()

        // then
        assertEquals(users, expectedUsers)
    }
}