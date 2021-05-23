package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.local.UserLocalDataSource
import com.picpay.desafio.android.data.remote.UserRemoteDataSource
import com.picpay.desafio.android.domain.User
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Test

import com.google.common.truth.Truth.assertThat
import org.junit.Before

class UserRepositoryImpTest {

    @MockK(relaxed = true)
    lateinit var mockLocalDataSource: UserLocalDataSource

    @MockK
    lateinit var mockRemoteDataSource: UserRemoteDataSource

    private lateinit var repository: UserRepositoryImp

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repository = UserRepositoryImp(
            localDataSource = mockLocalDataSource,
            remoteDataSource = mockRemoteDataSource
        )
    }

    @Test
    fun `Quando remoto retornar algum dado a função getUsers deve retornar do remoto`() {

        val expected = getUserList()

        coEvery { mockRemoteDataSource.getAllUser() } returns expected

        val result = runBlocking { (repository.getAllUser()) }

        assertThat(result).isEqualTo(expected.sortedBy { it.name })
    }


    @Test
    fun `Quando remoto retornar lista vazia,getUsers deve retornar do local`() {

        val expected = getUserList()

        coEvery { mockLocalDataSource.getAllUsers() } returns emptyList()
        coEvery { mockRemoteDataSource.getAllUser() } returns expected

        val result = runBlocking { repository.getAllUser() }

        assertThat(result).isEqualTo(expected.sortedBy { it.name })

    }

    private fun getUserList() = listOf(

        User(
            id = 1,
            name = "Sandrine Spinka",
            imgUrl = "https://randomuser.me/api/portraits/men/1.jpg",
            userName = "Tod86"
        ),
        User(
            id = 2,
            name = "Carli Carroll",
            imgUrl = "https://randomuser.me/api/portraits/men/2.jpg",
            userName = "Constantin_Sawayn"
        )
    )
}