package com.picpay.desafio.android.user.repository

import com.picpay.desafio.android.user.database.UserDataBase
import com.picpay.desafio.android.user.exception.UserServiceException
import com.picpay.desafio.android.user.mock.UserMock
import com.picpay.desafio.android.user.service.UserService
import io.mockk.coEvery
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.runBlocking
import okio.IOException
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import java.net.UnknownHostException

class UserRepositoryImplementTest {

    @Test(expected = UserServiceException::class)
    fun `ao chamar o getUsers quando o servico estiver com erro deve jogar uma UserServiceException`() {
        val serviceError: UserService = mockk()
        val mockException = UnknownHostException("mockError")
        coEvery { serviceError.getUsers() } throws mockException
        val repository = UserRepositoryImplement(serviceError, mockk())
        runBlocking {
            repository.getUsers()
        }
    }

    @Test
    fun `ao chamar o getUsersCache com database ok deve retorar result Success`() {
        val dataBase: UserDataBase = mockk()
        coEvery { dataBase.userDAO().getAllUsers() } returns UserMock.mockUserEntity()
        coEvery { dataBase.userDAO().deleteAndInsert(UserMock.mockUserEntity()) } just runs
        val repository: UserRepository = UserRepositoryImplement(mockk(), dataBase)
        runBlocking {
            val result = repository.getUsersCache()
            assertTrue(result.isSuccess)
            assertFalse(result.isFailure)
            assertEquals(UserMock.mockUserEntity(), result.getOrNull())
            assertNull(result.exceptionOrNull())
        }
    }

    @Test
    fun `ao chamar o getUsersCache com database com error deve retorar result Failure`() {
        val dataBase: UserDataBase = mockk()
        val exception = IOException("mockError")
        coEvery { dataBase.userDAO().getAllUsers() } throws exception
        coEvery { dataBase.userDAO().deleteAndInsert(UserMock.mockUserEntity()) } just runs
        val repository: UserRepository = UserRepositoryImplement(mockk(), dataBase)
        runBlocking {
            val result = repository.getUsersCache()
            assertTrue(result.isFailure)
            assertFalse(result.isSuccess)
            assertEquals(exception.cause, result.exceptionOrNull()?.cause)
            assertNull(result.getOrNull())
        }
    }

    @Test
    fun `ao chamar o getUsers quando o servico estiver ok deve retornar user list`() {
        val service: UserService = mockk()
        val dataBase: UserDataBase = mockk()
        coEvery { service.getUsers() } returns UserMock.mockUserResponse()
        coEvery { dataBase.userDAO().getAllUsers() } returns UserMock.mockUserEntity()
        coEvery { dataBase.userDAO().deleteAndInsert(UserMock.mockUserEntity()) } just runs

        val repository: UserRepository = UserRepositoryImplement(service, dataBase)
        runBlocking {
            val result = repository.getUsers()
            assertTrue(result.isSuccess)
            assertFalse(result.isFailure)
            assertNull(result.exceptionOrNull())
            assertEquals(UserMock.mockUserEntity(), result.getOrNull())
        }
    }
}