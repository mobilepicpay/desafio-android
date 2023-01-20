package com.picpay.desafio.android.data.repository

import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.UsersStub.listUsersEntity
import com.picpay.desafio.android.data.source.local.UserDao
import com.picpay.desafio.android.data.source.remote.UserRemoteDataSource
import com.picpay.desafio.android.domain.repository.UserRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito.mock
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class UserRepositoryImplTest {

    private val remoteDataSource: UserRemoteDataSource = mock()
    private val userDao: UserDao = mock()
    private val repository: UserRepository = UserRepositoryImpl(remoteDataSource, userDao)

    private val exception = RuntimeException("dummy")

    @Test
    fun `Given UserRepositoryImpl When call getUser than should return empty from database and remote empty`() =
        runTest {
            whenever(userDao.getAll()).thenReturn(listOf())
            whenever(remoteDataSource.getUsers()).thenReturn(listOf())

            val responseList = repository.getUser().toList()

            assertEquals(responseList, listOf(listOf()))
        }

    @Test
    fun `Given UserRepositoryImpl When call getUser than should return users from database and remote empty`() =
        runTest {
            whenever(userDao.getAll()).thenReturn(listUsersEntity)
            whenever(remoteDataSource.getUsers()).thenReturn(listOf())

            val responseList = repository.getUser().toList()

            assertEquals(responseList, listOf(listUsersEntity, listOf()))
        }

    @Test
    fun `Given UserRepositoryImpl When call getUser than should return empty from database and remote users`() =
        runTest {
            whenever(userDao.getAll()).thenReturn(listOf())
            whenever(remoteDataSource.getUsers()).thenReturn(listUsersEntity)

            val responseList = repository.getUser().toList()

            assertEquals(responseList, listOf(listUsersEntity))
        }

    @Test
    fun `Given UserRepositoryImpl When call getUser than should return error from database and remote users`() =
        runTest {
            whenever(userDao.getAll()).thenThrow(RuntimeException(""))
            whenever(remoteDataSource.getUsers()).thenReturn(listUsersEntity)

            val responseList = repository.getUser().toList()

            assertEquals(responseList, listOf(listUsersEntity))
        }

    @Test
    fun `Given UserRepositoryImpl When call getUser than should return empty from database and remote error`() =
        runTest {
            whenever(userDao.getAll()).thenReturn(listOf())
            whenever(remoteDataSource.getUsers()).thenThrow(exception)

            repository.getUser().catch {
                assertEquals(exception, it)
            }
        }

    @Test
    fun `Given UserRepositoryImpl When call getUser than should return users from database and remote error`() =
        runTest {
            whenever(userDao.getAll()).thenReturn(listUsersEntity)
            whenever(remoteDataSource.getUsers()).thenThrow(exception)

            val responseList = repository.getUser().toList()

            assertEquals(responseList, listOf(listUsersEntity))
        }

    @Test
    fun `Given UserRepositoryImpl When call getUpDateUsers than should return users `() =
        runTest {
            whenever(remoteDataSource.getUsers()).thenReturn(listUsersEntity)

            val responseList = repository.getUser().toList()

            assertEquals(responseList, listOf(listUsersEntity))
        }

    @Test
    fun `Given UserRepositoryImpl When call getUpDateUsers than should error`() =
        runTest {
            whenever(remoteDataSource.getUsers()).thenThrow(exception)

            repository.getUser().catch {
                assertEquals(exception, it)
            }
        }
}
