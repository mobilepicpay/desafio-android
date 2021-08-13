package com.picpay.desafio.android.user.repository

import com.picpay.desafio.android.network.ResultWrapper
import com.picpay.desafio.android.user.database.UserDaoTestMock
import com.picpay.desafio.android.user.database.UserEntity
import com.picpay.desafio.android.user.database.toDomain
import com.picpay.desafio.android.user.domain.UserDomain
import com.picpay.desafio.android.user.network.UserResponse
import com.picpay.desafio.android.user.network.UserService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class UserRepositoryTest {

    private val api = mockk<UserService>()
    private val dao = UserDaoTestMock()
    private val repository = UserRepository(api, dao)

    @Test
    fun `Should return wrapped user domain list when api succeeds`() = runBlockingTest {
        coEvery { api.getUsers() } returns listOf(
            UserResponse(img = "img", name = "name", id = 1, username = "username")
        )

        val expected = ResultWrapper.Success(listOf(UserDomain(
            imageUrl = "img", name = "name", id = 1, userName = "username")
        ))

        val actual = repository.getUsers().toList()
        assertEquals(expected.content, actual.last())
    }

    @Test
    fun `Should return wrapped user domain list when user dao is not empty`() = runBlockingTest {
        coEvery { api.getUsers() } returns emptyList()
        dao.insertAll(listOf(
            UserEntity(imageUrl = "img", name = "name", id = 1, userName = "username"))
        )

        val expected = ResultWrapper.Success(listOf(UserDomain(
            imageUrl = "img", name = "name", id = 1, userName = "username")
        ))

        val actual = repository.getUsers().toList()
        assertEquals(expected.content, actual.first())
    }

    @Test
    fun `Should fill user dao data when api succeeds`() = runBlockingTest {
        coEvery { api.getUsers() } returns listOf(
            UserResponse(img = "img", name = "name", id = 1, username = "username")
        )

        repository.getUsers().toList()

        val expected = listOf(UserDomain(
            imageUrl = "img", name = "name", id = 1, userName = "username")
        )

        val actual = dao.getAll().toDomain()
        assertEquals(expected, actual)
    }

    @Test
    fun `Should replace user dao data when api succeeds with new users`() = runBlockingTest {
        coEvery { api.getUsers() } returns listOf(
            UserResponse(img = "img2", name = "name2", id = 2, username = "username2")
        )
        dao.insertAll(listOf(
            UserEntity(imageUrl = "img1", name = "name1", id = 1, userName = "username1"))
        )

        repository.getUsers().toList()

        val expected = listOf(UserDomain(
            imageUrl = "img2", name = "name2", id = 2, userName = "username2")
        )

        val actual = dao.getAll().toDomain()
        assertEquals(expected, actual)
    }

    @Test
    fun `Should return wrapped error when api fails`() = runBlockingTest {
        val throwable = Throwable(message = "error")
        coEvery { api.getUsers() } throws throwable

        repository.getUsers()
            .catch { cause -> assertEquals(throwable.message, cause.message) }
            .single()
    }
}