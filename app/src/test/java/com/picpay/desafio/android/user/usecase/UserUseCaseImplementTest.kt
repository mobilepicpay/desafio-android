package com.picpay.desafio.android.user.usecase

import com.picpay.desafio.android.user.exception.UserServiceException
import com.picpay.desafio.android.user.mock.UserMock
import com.picpay.desafio.android.user.repository.UserRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.IOException

class UserUseCaseImplementTest {

    @Test
    fun `ao consultar getUsers e o repository estiver ok dever retornar sucesso`() {
        val repository: UserRepository = mockk()
        coEvery { repository.getUsers() } returns UserMock.mockUserSuccess()
        val useCase: UserUseCase = UserUseCaseImplement(repository)
        runBlocking {
            val result = useCase.getUsers()
            assertTrue(result.isSuccess)
            assertFalse(result.isFailure)
            assertNull(result.exceptionOrNull())
            assertEquals(UserMock.mockUserEntity(), result.getOrNull())
        }
    }

    @Test
    fun `ao consultar getUsers e o servico estiver fora e houver cache dever retornar sucesso`() {
        val repository: UserRepository = mockk()
        val exception = UserServiceException(IOException("mockError"))
        coEvery { repository.getUsers() } throws exception
        coEvery { repository.getUsersCache() } returns UserMock.mockUserSuccess()
        val useCase: UserUseCase = UserUseCaseImplement(repository)
        runBlocking {
            val result = useCase.getUsers()
            assertTrue(result.isSuccess)
            assertFalse(result.isFailure)
            assertNull(result.exceptionOrNull())
            assertEquals(UserMock.mockUserEntity(), result.getOrNull())
        }
    }

    @Test
    fun `ao consultar getUsers e o servico estiver ok mas retornar lista vazia ou nula deve retornar falha`() {
        val repository: UserRepository = mockk()
        coEvery { repository.getUsers() } returns UserMock.mockUserSuccessEmpty()
        val useCase: UserUseCase = UserUseCaseImplement(repository)
        runBlocking {
            val result = useCase.getUsers()
            assertTrue(result.isFailure)
            assertFalse(result.isSuccess)
            assertNull(result.getOrNull())
            assertEquals("lista vazia", result.exceptionOrNull()?.message)
        }
    }

    @Test
    fun `ao consultar getUsers e o servico estiver fora e base vazia deve retornar falha`() {
        val repository: UserRepository = mockk()
        val exception = UserServiceException(IOException("mockError"))
        coEvery { repository.getUsers() } throws exception
        coEvery { repository.getUsersCache() } returns UserMock.mockUserSuccessEmpty()
        val useCase: UserUseCase = UserUseCaseImplement(repository)
        runBlocking {
            val result = useCase.getUsers()
            assertTrue(result.isFailure)
            assertFalse(result.isSuccess)
            assertNull(result.getOrNull())
            assertEquals("lista vazia", result.exceptionOrNull()?.message)
        }
    }

    @Test
    fun `ao consultar getUsers e o servico estiver fora e base fora deve retornar falha`() {
        val repository: UserRepository = mockk()
        val exception = UserServiceException(IOException("mockError"))
        coEvery { repository.getUsers() } throws exception
        coEvery { repository.getUsersCache() } returns UserMock.mockUserFailure(exception)
        val useCase: UserUseCase = UserUseCaseImplement(repository)
        runBlocking {
            val result = useCase.getUsers()
            assertTrue(result.isFailure)
            assertFalse(result.isSuccess)
            assertNull(result.getOrNull())
            assertEquals(exception, result.exceptionOrNull())
        }
    }
}