package com.picpay.desafio.android

import com.picpay.desafio.android.data.local.UserDao
import com.picpay.desafio.android.data.remote.PicPayService
import com.picpay.desafio.android.data.repository.UserRepositoryImpl
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.repository.UserRepository
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class UserRepositoryTest {

    private lateinit var repository: UserRepository
    private val api: PicPayService = mock()
    private val dao: UserDao = mock()

    @Before
    fun setup() {
        repository = UserRepositoryImpl(api, dao)
    }

    @Test
    fun givenCachedUsersWhenGetUsersIsCalledThenReturnCachedUsers() {
        runBlocking {
            val cachedUsers = listOf(User(1, "url", "User Test", "usertest"))
            whenever(dao.getAllUsers()).thenReturn(flowOf(cachedUsers))

            val result = repository.getUsers()

            assertEquals(cachedUsers, result)
            verify(api, never()).getUsers()
        }
    }

    @Test
    fun givenEmptyCacheWhenGetUsersIsCalledThenFetchFromApiAndStoreInCache() {
        runBlocking {
            val apiUsers = listOf(User(1, "url", "User Test", "usertest"))
            whenever(dao.getAllUsers()).thenReturn(flowOf(emptyList()))
            whenever(api.getUsers()).thenReturn(apiUsers)

            val result = repository.getUsers()

            assertEquals(apiUsers, result)
            verify(dao).insertUsers(apiUsers)
        }
    }
}
