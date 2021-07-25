package com.picpay.desafio.android.data.source.local

import com.google.common.truth.Truth
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
class LocalDataSourceTest {

    private val dao = mockk<UserDao>(relaxed = true)
    private val listResponse = listOf(UserDb(1, "url", "alessandro", "ale"))

    @Test
    fun `when get users from dao, check values in flow`() = runBlocking {
        every {
            dao.getAllUsers()
        } returns flow {
            emit(listResponse)
        }

        val localDataSource = LocalDataSource(dao)

        val flow = localDataSource.getAllUsers()

        flow.collect { result ->
            Truth.assertThat(result).isEqualTo(listResponse)
        }
    }

    @Test
    fun `when local data source insert users check if dao was called`() = runBlockingTest {
        coEvery {
            dao.insertUsers(any())
        } just runs

        val localDataSource = LocalDataSource(dao)
        localDataSource.insertUsers(listResponse)

        coVerify(exactly = 1) {
            dao.insertUsers(listResponse)
        }
    }

    @Test
    fun `when local data source delete users check if dao was called`() = runBlockingTest {
        coEvery {
            dao.deleteAllUsers()
        } just runs

        val localDataSource = LocalDataSource(dao)
        localDataSource.deleteUsers()

        coVerify(exactly = 1) {
            dao.deleteAllUsers()
        }
    }

}