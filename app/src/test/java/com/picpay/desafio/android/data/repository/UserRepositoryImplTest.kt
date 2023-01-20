package com.picpay.desafio.android.data.repository

import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.data.entity.UserEntity
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

    private val listUsers = listOf(
        UserEntity(img = "img 1", name = "name 1", username = "user name 1", id = 1),
        UserEntity(img = "img 2", name = "name 2", username = "user name 2", id = 2)
    )

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
            whenever(userDao.getAll()).thenReturn(listUsers)
            whenever(remoteDataSource.getUsers()).thenReturn(listOf())

            val responseList = repository.getUser().toList()

            assertEquals(responseList, listOf(listUsers, listOf()))
        }

    @Test
    fun `Given UserRepositoryImpl When call getUser than should return empty from database and remote users`() =
        runTest {
            whenever(userDao.getAll()).thenReturn(listOf())
            whenever(remoteDataSource.getUsers()).thenReturn(listUsers)

            val responseList = repository.getUser().toList()

            assertEquals(responseList, listOf(listUsers))
        }

    @Test
    fun `Given UserRepositoryImpl When call getUser than should return error from database and remote users`() =
        runTest {
            whenever(userDao.getAll()).thenThrow(RuntimeException(""))
            whenever(remoteDataSource.getUsers()).thenReturn(listUsers)

            val responseList = repository.getUser().toList()

            assertEquals(responseList, listOf(listUsers))
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
            whenever(userDao.getAll()).thenReturn(listUsers)
            whenever(remoteDataSource.getUsers()).thenThrow(exception)

            val responseList = repository.getUser().toList()

            assertEquals(responseList, listOf(listUsers))
        }

    @Test
    fun `Given UserRepositoryImpl When call getUpDateUsers than should return users `() =
        runTest {
            whenever(remoteDataSource.getUsers()).thenReturn(listUsers)

            val responseList = repository.getUser().toList()

            assertEquals(responseList, listOf(listUsers))
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
