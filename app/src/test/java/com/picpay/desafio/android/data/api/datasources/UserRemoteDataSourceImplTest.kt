package com.picpay.desafio.android.data.api.datasources

import com.picpay.desafio.android.data.api.mappers.UserRemoteMapper
import com.picpay.desafio.android.data.api.responses.UserResponse
import com.picpay.desafio.android.data.api.webservices.UserService
import com.picpay.desafio.android.models.User
import com.picpay.desafio.android.utils.sealeds.DataErrorException
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.net.UnknownHostException

@ExperimentalCoroutinesApi
class UserRemoteDataSourceImplTest {

    @MockK
    private lateinit var userService: UserService

    @MockK
    private lateinit var userRemoteMapper: UserRemoteMapper

    private lateinit var userRemoteDataSource: UserRemoteDataSource

    private val users = listOf(
        User(1, "John", "image url", "John10"),
        User(2, "Ana", "image url", "Ana07")
    )

    private val userResponse = listOf(
        UserResponse("image url", "John", 1, "John10"),
        UserResponse("image url", "Ana", 2, "Ana07")
    )

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true, relaxed = true)

        userRemoteDataSource = UserRemoteDataSourceImpl(userService, userRemoteMapper)
    }

    @After
    fun teardown() {
        unmockkAll()
    }

    @Test
    fun `getUsers, when it successfully fetches users, it returns all the users that came in the response`() {
        coEvery { userService.getUsers() }.answers { userResponse }
        every { userRemoteMapper.fromResponseToModel(userResponse.first()) }.returns(users.first())
        every { userRemoteMapper.fromResponseToModel(userResponse.last()) }.returns(users.last())

        runBlockingTest {
            val users = userRemoteDataSource.getUsers()

            assertTrue(users.size == 2)
            assertTrue(users.contains(users.first()))
            assertTrue(users.contains(users.last()))
        }
    }

    @Test(expected = DataErrorException.ApiErrorException::class)
    fun `getUsers, when it fails to fetch users, it throws an api exception`() {
        coEvery { userService.getUsers() }.throws(UnknownHostException())

        runBlockingTest {
            userRemoteDataSource.getUsers()
        }
    }
}
