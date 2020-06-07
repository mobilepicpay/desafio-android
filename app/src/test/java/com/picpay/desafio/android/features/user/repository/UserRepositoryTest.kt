package com.picpay.desafio.android.features.user.repository

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.stub
import com.picpay.desafio.android.base.OnError
import com.picpay.desafio.android.base.OnSuccess
import com.picpay.desafio.android.custom.aliases.ListOfUsers
import com.picpay.desafio.android.custom.aliases.UserResult
import com.picpay.desafio.android.custom.aliases.UsersResult
import com.picpay.desafio.android.custom.errors.NetworkException
import com.picpay.desafio.android.custom.errors.NoDataException
import com.picpay.desafio.android.features.user.network.api.UserService
import com.picpay.desafio.android.features.user.network.response.UserResponse
import com.picpay.desafio.android.room.dao.KeyValueDao
import com.picpay.desafio.android.room.dao.UserDao
import com.picpay.desafio.android.room.models.User
import com.picpay.desafio.android.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Response


class UserRepositoryTest {

    private val userDao = mock<UserDao>()
    private val picPayService = mock<UserService>()
    private val keyValueDao = mock<KeyValueDao>()

    private val testDispatcher = TestCoroutineDispatcher()

    private val userRepository =
        UserRepository(userDao, keyValueDao, picPayService, testDispatcher)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `test getUsers for valid response`() = runBlockingTest {

        // Mock service
        val response = listOf(
            UserResponse(1, "User 1", "avatar_url", "user_name"),
            UserResponse(2, "User 2", "avatar_url", "user_name"),
            UserResponse(3, "User 3", "avatar_url", "user_name")
        )

        picPayService.stub {
            onBlocking { getUsers() } doReturn Response.success(200, response)
        }

        keyValueDao.stub {
            onBlocking { get(Constants.LAST_USER_API_CALL_TIMESTAMP) } doReturn null
        }

        // Test
        val flow = userRepository.getUsers()

        // Verify
        var success = false
        flow.collect { result: UsersResult ->
            when (result) {
                is OnSuccess<ListOfUsers> -> {
                    success = true
                    assertEquals(result.data.size, response.size)
                }
            }
        }

        assertTrue(success)
    }

    @Test
    fun `test getUsers for empty response`() = runBlockingTest {

        // Mock service
        picPayService.stub {
            onBlocking { getUsers() } doReturn Response.success(200, arrayListOf())
        }

        keyValueDao.stub {
            onBlocking { get(Constants.LAST_USER_API_CALL_TIMESTAMP) } doReturn null
        }

        // Test
        val flow = userRepository.getUsers()

        // Verify
        var error = false
        flow.collect { result: UsersResult ->
            when (result) {
                is OnError -> {
                    error = true
                    assert(result.exception is NoDataException)
                }
            }
        }

        assertTrue(error)
    }

    @Test
    fun `test getUsers for error`() = runBlockingTest {

        // Mock service
        val response = ""
        picPayService.stub {
            onBlocking { getUsers() } doReturn Response.error(404, response.toResponseBody(null))
        }

        keyValueDao.stub {
            onBlocking { get(Constants.LAST_USER_API_CALL_TIMESTAMP) } doReturn null
        }

        // Test
        val flow = userRepository.getUsers()

        // Verify
        var error = false
        flow.collect { result: UsersResult ->
            when (result) {
                is OnError -> {
                    error = true
                    assert(result.exception is NetworkException)
                }
            }
        }

        assertTrue(error)
    }

    @Test
    fun `Test getUser success`() = runBlockingTest {

        // Mock
        val user = User(1, "User 1", "avatar_url", "user_name")
        userDao.stub {
            onBlocking { getUser(1) } doReturn user
        }

        // Test
        val flow = userRepository.getUser(1)

        // Verify
        flow.collect { result: UserResult ->
            assert(result is OnSuccess<User>)
            assertEquals((result as OnSuccess<User>).data.id, user.id)
        }
    }

    @Test
    fun `Test getUser failure`() = runBlockingTest {

        // Mock
        userDao.stub {
            onBlocking { getUser(1) } doReturn null
        }

        // Test
        val flow = userRepository.getUser(1)

        // Verify
        flow.collect { result: UserResult ->
            assert(result is OnError)
            assert((result as OnError).exception is NoDataException)
        }
    }
}