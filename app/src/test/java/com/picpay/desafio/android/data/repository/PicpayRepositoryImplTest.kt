package com.picpay.desafio.android.data.repository

import com.google.common.truth.Truth
import com.picpay.desafio.android.coreNetwork.models.Response
import com.picpay.desafio.android.coreNetwork.retrofit.client.NetworkManager
import com.picpay.desafio.android.data.datasource.PicpayLocalDataSource
import com.picpay.desafio.android.data.datasource.PicpayRemoteDatasource
import com.picpay.desafio.android.data.model.UserResponse
import com.picpay.desafio.android.domain.repository.PicpayRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class PicpayRepositoryImplTest {

    private lateinit var repository: PicpayRepository
    private val datasource: PicpayRemoteDatasource = mockk()
    private val localDatasource: PicpayLocalDataSource = mockk()
    private val manager: NetworkManager = mockk()

    @Before
    fun setUp() {
        repository = PicpayRepositoryImpl(
            datasource = datasource,
            localDataSource = localDatasource,
            manager = manager
        )
    }

    @Test
    fun `Should call local datasource when network is avaiable and cache is active`() =
        runBlocking {
            coEvery { manager.isNetworkAvailable() } returns true
            coEvery { localDatasource.checkCacheExpired() } returns false
            coEvery { localDatasource.getUsers() } returns flow {
                emit(Response.Success(listOf()))
            }

            repository.getUsers().first()

            coVerify(exactly = 1) { localDatasource.getUsers() }
            coVerify(inverse = true) { datasource.getUsers() }
            coVerify(exactly = 1) { manager.isNetworkAvailable() }
        }

    @Test
    fun `Should call local dataSource when network is unavailable`() = runBlocking {
        runBlocking {
            coEvery { manager.isNetworkAvailable() } returns true
            coEvery { localDatasource.checkCacheExpired() } returns false
            coEvery { localDatasource.getUsers() } returns flow {
                emit(Response.Success(listOf()))
            }

            repository.getUsers().first()

            coVerify(exactly = 1) { localDatasource.getUsers() }
            coVerify(inverse = true) { datasource.getUsers() }
            coVerify(exactly = 1) { manager.isNetworkAvailable() }
        }
    }

    @Test
    fun `should call remote datasource when network is available and cache is expired`() =
        runBlocking {
            coEvery { manager.isNetworkAvailable() } returns true
            coEvery { localDatasource.checkCacheExpired() } returns true
            coEvery { datasource.getUsers() } returns flow {
                emit(Response.Success(listOf()))
            }
            coEvery { localDatasource.saveUsers(any()) } returns Unit

            repository.getUsers().first()

            coVerify(exactly = 1) { datasource.getUsers() }
            coVerify(inverse = true) { localDatasource.getUsers() }
            coVerify(exactly = 1) { manager.isNetworkAvailable() }
        }

    @Test
    fun `should cache results when remote datasource is called`() =
        runBlocking {
            coEvery { manager.isNetworkAvailable() } returns true
            coEvery { localDatasource.checkCacheExpired() } returns true
            coEvery { datasource.getUsers() } returns flow {
                emit(Response.Success(listOf()))
            }
            coEvery { localDatasource.saveUsers(any()) } returns Unit

            repository.getUsers().first()

            coVerify(exactly = 1) { localDatasource.saveUsers(any()) }
        }

    @Test
    fun `should filter users with null id when remote datasource is called`() = runBlocking {
        coEvery { manager.isNetworkAvailable() } returns true
        coEvery { localDatasource.checkCacheExpired() } returns true
        coEvery { datasource.getUsers() } returns flow {
            emit(
                Response.Success(
                    listOf(
                        UserResponse(null, null, null, null),
                        UserResponse(null, null, 10, null)
                    )
                )
            )
        }
        coEvery { localDatasource.saveUsers(any()) } returns Unit

        repository.getUsers().first()

        val response = repository.getUsers().first() as Response.Success

        Truth.assertThat(response.data).hasSize(1)
    }

}
