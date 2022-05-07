package com.picpay.desafio.android.domain.usecase

import com.picpay.desafio.android.core_network.models.Response
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

internal class UsersUseCaseTest {

    private lateinit var useCase: UsersInteractor
    private val repository: PicpayRepository = mockk()

    @Before
    fun setUp() {
        useCase = UsersUseCase(
            repository = repository
        )
    }

    @Test
    fun `should call repository when use case is called`() = runBlocking {
        coEvery { repository.getUsers() } returns flow {
            emit(Response.Success(listOf()))
        }

        useCase.getUsers().first()

        coVerify(exactly = 1) { repository.getUsers() }
        confirmVerified(repository)

    }

}