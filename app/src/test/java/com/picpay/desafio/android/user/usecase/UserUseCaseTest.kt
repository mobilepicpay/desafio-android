package com.picpay.desafio.android.user.usecase

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.user.listContactsSuccess
import com.picpay.desafio.android.user.mockListUser
import com.picpay.desafio.android.user.repository.UserRepository
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class UserUseCaseTest {
    private val mockUserRepository = mock<UserRepository>()
    private val useCase = UserUseCase(mockUserRepository)

    @Test
    fun `Ao chamar metodo getContacts() com 1 item no local, deve retornar lista de contatos com 1 item vindo do local`(): Unit =
        runBlocking {
            whenever(mockUserRepository.getContactsLocal()).thenReturn(mockListUser)

            // when
            val users = useCase.getContacts()

            // then
            assertEquals(listContactsSuccess, users)
        }

    @Test
    fun `Ao chamar metodo getContacts() com 0 itens no local, deve retornar lista de contatos com 1 item vindo do remoto`(): Unit =
        runBlocking {
            whenever(mockUserRepository.getContactsLocal()).thenReturn(mockListUser)
            whenever(mockUserRepository.getContactsRemote()).thenReturn(mockListUser)

            // when
            val users = useCase.getContacts()

            // then
            assertEquals(listContactsSuccess, users)
        }
}