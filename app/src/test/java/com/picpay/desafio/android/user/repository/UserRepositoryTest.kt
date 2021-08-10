package com.picpay.desafio.android.user.repository

import com.picpay.desafio.android.network.ResultWrapper
import com.picpay.desafio.android.user.domain.UserDomain
import com.picpay.desafio.android.user.network.UserResponse
import com.picpay.desafio.android.user.network.UserService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class UserRepositoryTest {

    private val api = mockk<UserService>()
    private val repository = UserRepository(api)

    @Test
    fun `Should return wrapped user domain list when api succeeds`() = runBlockingTest {
        coEvery { api.getUsers() } returns listOf(
            UserResponse(img = "img", name = "name", id = 1, username = "username")
        )

        val expected = ResultWrapper.Success(listOf(UserDomain(
            imageUrl = "img", name = "name", id = 1, userName = "username")
        ))

        val actual = repository.getUsers() as ResultWrapper.Success
        assertEquals(expected.content, actual.content)
    }

    @Test
    fun `Should return wrapped error when api fails`() = runBlockingTest {
        val throwable = Throwable(message = "error")
        coEvery { api.getUsers() } throws throwable

        val actual = repository.getUsers() as ResultWrapper.Error
        assertEquals(throwable, actual.throwable)
    }
}