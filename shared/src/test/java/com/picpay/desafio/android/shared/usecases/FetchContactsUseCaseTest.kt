package com.picpay.desafio.android.shared.usecases

import com.picpay.desafio.android.shared.model.ResultWrapper
import com.picpay.desafio.android.shared.model.User
import com.picpay.desafio.android.shared.model.ViewUser
import com.picpay.desafio.android.shared.services.PicPayService
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * @author Vitor Herrmann on 13/07/2021
 */

@ExperimentalCoroutinesApi
class FetchContactsUseCaseTest {

    private lateinit var useCase: FetchContactsUseCase
    private val picPayService = mockk<PicPayService>()

    @Before
    fun setup() {
        useCase = FetchContactsUseCaseImpl(picPayService)
    }

    @Test
    fun `fetch contacts successfully and map them do ViewUser`() = runBlockingTest {
        coEvery { picPayService.getUsers() } returns listOf(
            User("imagem 1", "nome 1", 1, "usuario 1"),
            User("imagem 2", "nome 2", 2, "usuario 2")
        )

        val actual = useCase.fetchContacts()

        Assert.assertEquals(
            ResultWrapper.Success(
                listOf(
                    ViewUser("imagem 1", "nome 1", "usuario 1"),
                    ViewUser("imagem 2", "nome 2", "usuario 2"),
                )
            ),
            actual
        )

        coVerify(exactly = 1) { picPayService.getUsers() }

        confirmVerified(picPayService)
    }
}
