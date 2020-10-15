package com.picpay.desafio.android.user.repository

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.user.mockListUser
import com.picpay.desafio.android.user.mockListUserEntity
import com.picpay.desafio.android.user.model.User
import com.picpay.desafio.android.user.repository.local.UserLocalDataSource
import com.picpay.desafio.android.user.repository.remote.UserRemoteDataSource
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class UserRepositoryTest {
    private val mockRemoteDataSource = mock<UserRemoteDataSource>()
    private val mockLocalDataSource = mock<UserLocalDataSource>()
    private val repository = UserRepository(mockRemoteDataSource, mockLocalDataSource)

    @Test
    fun `Ao chamar metodo getContacts() com 1 item no remoto, deve retornar lista de contatos com 1 item`(): Unit =
        runBlocking {
            whenever(mockRemoteDataSource.getUsers()).thenReturn(mockListUser)

            // when
            val users = repository.getContactsRemote()

            // then
            assertEquals(mockListUser, users)
        }

    @Test
    fun `Ao chamar metodo getContacts() com 0 itens no remoto, deve retornar lista vazia`(): Unit =
        runBlocking {
            whenever(mockRemoteDataSource.getUsers()).thenReturn(arrayListOf())

            // when
            val users = repository.getContactsRemote()

            // then
            assertEquals(arrayListOf<User>(), users)
        }

    @Test
    fun `Ao chamar metodo getContactsCache() com 1 item no local, deve retornar lista de contatos com 1 item`(): Unit =
        runBlocking {
            whenever(mockLocalDataSource.getAllUsers()).thenReturn(mockListUserEntity)

            // when
            val users = repository.getContactsLocal()

            // then
            assertEquals(mockListUser, users)
        }

    @Test
    fun `Ao chamar metodo getContactsCache() com 0 itens no banco local, deve retornar lista vazia`(): Unit =
        runBlocking {
            whenever(mockLocalDataSource.getAllUsers()).thenReturn(arrayListOf())

            // when
            val users = repository.getContactsLocal()

            // then
            assertEquals(arrayListOf<User>(), users)
        }
}