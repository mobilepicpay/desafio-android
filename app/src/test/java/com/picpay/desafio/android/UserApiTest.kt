package com.picpay.desafio.android

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.users.repo.UserResponse
import com.picpay.desafio.android.users.repo.UsersApi
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import retrofit2.Response

class UserApiTest {

    private val api = mock<UsersApi>()

    private val service = UsersApiServiceTest(api)

    @Test
    fun checkSuccessResponse() {

        runBlocking {
            // given
            val expectedUsers = emptyList<UserResponse>()
            // when
            whenever(api.getUsers()).thenReturn(Response.success(expectedUsers))

            val users = service.getUsers()

            // then
            assertEquals(users, expectedUsers)
        }
    }
}