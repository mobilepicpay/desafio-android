package com.picpay.desafio.android.data.repository

import com.google.common.truth.Truth
import com.picpay.desafio.android.core_network.models.Response
import com.picpay.desafio.android.data.datasource.PicpayRemoteDatasource
import com.picpay.desafio.android.data.model.UserResponse
import com.picpay.desafio.android.domain.repository.PicpayRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class PicpayRepositoryImplTest {

    private lateinit var repository: PicpayRepository
    private val datasource: PicpayRemoteDatasource = mockk()

    @Before
    fun setUp() {
        repository = PicpayRepositoryImpl(
            datasource = datasource
        )
    }

    @Test
    fun `should call datasource when get users is called`() = runBlocking {
        coEvery { datasource.getUsers() } returns flow {
            emit(Response.Success(listOf()))
        }

        repository.getUsers().first()

        coVerify(exactly = 1) { datasource.getUsers() }
        confirmVerified(datasource)
    }

    @Test
    fun `should filter users with null id`() = runBlocking {
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

        val response = repository.getUsers().first() as Response.Success

        Truth.assertThat(response.data).hasSize(1)
    }

}