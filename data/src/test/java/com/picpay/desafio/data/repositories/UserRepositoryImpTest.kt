package com.picpay.desafio.data.repositories

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.stub
import com.picpay.desafio.data.apiservice.UserService
import com.picpay.desafio.data.exceptions.NetworkException
import com.picpay.desafio.data.exceptions.NoDataException
import com.picpay.desafio.data.db.dao.KeyValueDao
import com.picpay.desafio.data.db.dao.UserDao
import com.picpay.desafio.data.db.models.UserData
import com.picpay.desafio.data.models.UserResponse
import com.picpay.desafio.data.utils.Constants
import com.picpay.desafio.domain.result.OnError
import com.picpay.desafio.domain.result.OnSuccess
import com.picpay.desafio.domain.result.Result
import com.picpay.desafio.domain.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class UserRepositoryImpTest {

    private val userDao = mock<UserDao>()
    private val picPayService = mock<UserService>()
    private val keyValueDao = mock<KeyValueDao>()

    private val testDispatcher = TestCoroutineDispatcher()

    private val userRepository =
        UserRepositoryImp(userDao, keyValueDao, picPayService, testDispatcher)

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

        flow.collect { result: Result<List<User>> ->
            when (result) {
                is OnSuccess<List<User>> -> {
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
        flow.collect { result: Result<List<User>> ->
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
            onBlocking { getUsers() } doReturn Response.error(404, "".toResponseBody())
        }

        keyValueDao.stub {
            onBlocking { get(Constants.LAST_USER_API_CALL_TIMESTAMP) } doReturn null
        }

        // Test
        val flow = userRepository.getUsers()

        // Verify
        var error = false
        flow.collect { result: Result<List<User>> ->
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
    fun `Test getUser success`() = runBlocking {

        // Mock
        val userData = UserData(1, "User 1", "avatar_url", "user_name")
        userDao.stub {
            onBlocking { getUser(1) } doReturn userData
        }

        // Test
        val flow = userRepository.getUser(1)

        // Verify
        flow.collect { result: Result<User> ->
            assert(result is OnSuccess<User>)
            assertEquals((result as OnSuccess<User>).data.id, userData.id)
        }
    }

    @Test
    fun `Test getUser failure`() = runBlocking {

        // Mock
        userDao.stub {
            onBlocking { getUser(1) } doReturn null
        }

        // Test
        val flow = userRepository.getUser(1)

        // Verify
        flow.collect { result: Result<User> ->
            assert(result is OnError)
            assert((result as OnError).exception is NoDataException)
        }
    }
}