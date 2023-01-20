package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.UsersStub.listUsersEntity
import com.picpay.desafio.android.data.source.local.UserDao
import com.picpay.desafio.android.data.source.remote.UserRemoteDataSourceImpl
import com.picpay.desafio.android.domain.repository.UserRepository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class UserRepositoryImplTest {

    private val remoteDataSource: UserRemoteDataSourceImpl = mockk()
    private val userDao: UserDao = mockk()
    private val repository: UserRepository = UserRepositoryImpl(remoteDataSource, userDao)

    private val exception = RuntimeException("dummy")

    @Before
    fun setup() {
        every { userDao.deleteAll() } just runs
        every { userDao.insertAll(any()) } just runs
    }

    @Test
    fun `Given UserRepositoryImpl When call getUser than should return empty from database and remote empty`() =
        runTest {
            every { userDao.getAll() } returns listOf()
            coEvery { remoteDataSource.getUsers() } returns listOf()

            val responseList = repository.getUser().toList()

            assertEquals(responseList, listOf(listOf()))
        }

    @Test
    fun `Given UserRepositoryImpl When call getUser than should return users from database and remote empty`() =
        runTest {
            every { userDao.getAll() } returns listUsersEntity
            coEvery { remoteDataSource.getUsers() } returns listOf()

            val responseList = repository.getUser().toList()

            assertEquals(responseList, listOf(listUsersEntity, listOf()))
        }

    @Test
    fun `Given UserRepositoryImpl When call getUser than should return empty from database and remote users`() =
        runTest {
            every { userDao.getAll() } returns listOf()
            coEvery { remoteDataSource.getUsers() } returns listUsersEntity

            val responseList = repository.getUser().toList()

            assertEquals(responseList, listOf(listUsersEntity))
        }

    @Test
    fun `Given UserRepositoryImpl When call getUser than should return error from database and remote users`() =
        runTest {
            every { userDao.getAll() } throws RuntimeException("")
            coEvery { remoteDataSource.getUsers() } returns listUsersEntity

            val responseList = repository.getUser().toList()

            assertEquals(responseList, listOf(listUsersEntity))
        }

    @Test
    fun `Given UserRepositoryImpl When call getUser than should return empty from database and remote error`() =
        runTest {
            every { userDao.getAll() } returns listOf()
            coEvery { remoteDataSource.getUsers() } throws exception

            repository.getUser().catch {
                assertEquals(exception, it)
            }
        }

    @Test
    fun `Given UserRepositoryImpl When call getUser than should return users from database and remote error`() =
        runTest {
            every { userDao.getAll() } returns listUsersEntity
            coEvery { remoteDataSource.getUsers() } throws exception

            val responseList = repository.getUser().toList()

            assertEquals(responseList, listOf(listUsersEntity))
        }

    @Test
    fun `Given UserRepositoryImpl When call getUpDateUsers than should return users `() =
        runTest {
            coEvery { remoteDataSource.getUsers() } returns listUsersEntity

            val responseList = repository.getUser().toList()

            assertEquals(responseList, listOf(listUsersEntity))
        }

    @Test
    fun `Given UserRepositoryImpl When call getUpDateUsers than should error`() =
        runTest {
            coEvery { remoteDataSource.getUsers() } throws exception

            repository.getUser().catch {
                assertEquals(exception, it)
            }
        }
}
