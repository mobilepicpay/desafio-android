package com.picpay.desafio.android.data.source.remote

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.data.entity.UserEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import retrofit2.Response
import java.lang.RuntimeException
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@ExperimentalCoroutinesApi
class UserRemoteDataSourceTest {

    private val api = mock<PicPayService>()

    private val remoteDataSource = UserRemoteDataSourceImpl(api)

    @Test
    fun `Given UserRemoteDataSource When call getUsers Than should return list of users `() =
        runTest {
            val expectedUsers = listOf(
                UserEntity("img1", "name1", 1L, "username1"),
                UserEntity("img2", "name2", 2L, "username2")
            )

            whenever(api.getUsers()).thenReturn(Response.success(expectedUsers))

            assertEquals(expectedUsers, remoteDataSource.getUsers())
        }

    @Test
    fun `Given UserRemoteDataSource When call getUsers Than return error `() = runTest {
        whenever(api.getUsers()).thenThrow(RuntimeException("dummy"))

        assertFailsWith<RuntimeException> { remoteDataSource.getUsers() }
    }

    @Test
    fun `Given UserRemoteDataSource When call getUsers Than body null `() = runTest {
        whenever(api.getUsers()).thenReturn(Response.success(null))

        assertFailsWith<Exception> { remoteDataSource.getUsers() }
    }

    @Test
    fun `Given UserRemoteDataSource When call getUsers Than should return empty list`() = runTest {
        val expectedUsers = emptyList<UserEntity>()

        whenever(api.getUsers()).thenReturn(Response.success(expectedUsers))

        assertEquals(expectedUsers, remoteDataSource.getUsers())
    }
}
