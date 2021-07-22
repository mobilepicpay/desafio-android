package com.picpay.desafio.android.ui.viewmodel

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.data.local.datasource.UserLocalDataSource
import com.picpay.desafio.android.data.model.User
import com.picpay.desafio.android.data.remote.datasource.UserRemoteDataSource
import com.picpay.desafio.android.data.repository.UserDataRepositoryImpl
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
class UserDataRepositoryTest {

    private val mockRemote = mock<UserRemoteDataSource>()
    private val mockLocal = mock<UserLocalDataSource>()

    private val repository = UserDataRepositoryImpl(mockRemote, mockLocal)

    @Test
     fun `when get user list remote`() = runBlocking {
        val userList = listUser

            whenever(mockRemote.getUser()).thenReturn(userList)

            // when
            val users = repository.getUserList()

            // then
            assertEquals(userList, users)

    }

    @Test
    fun `when get user list local`() = runBlocking {
        val userList = listUser

        whenever(mockLocal.getUserData()).thenReturn(userList)

        // when
        val users = repository.getUserLocal()

        // then
        assertEquals(userList, users)

    }


    private val listUser = listOf(User(
        id = 1,
        name = "name",
        username = "username",
        img = "http://"
    ))
}