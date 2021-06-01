package com.picpay.desafio.android.data.remote

import com.picpay.desafio.android.data.api.UserService
import com.picpay.desafio.android.data.mappers.UserResponseMapper
import com.picpay.desafio.android.domain.User
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import com.google.common.truth.Truth.assertThat
import com.picpay.desafio.android.data.model.UserResponse
import java.io.IOException


class UserRemoteDataSourceImpTest {

    @MockK
    lateinit var mockService: UserService

    private lateinit var remoteDataSource: UserRemoteDataSourceImp

    @Before
    fun setUp() {

        MockKAnnotations.init(this)

        val mapper = UserResponseMapper()

        remoteDataSource = UserRemoteDataSourceImp(
            service = mockService,
            userResponseMapper = mapper
        )
    }

    @Test
    fun `Quando Service retornar uma lista de Users, deve retornar uma lista de User como dados Domain `() {

        coEvery { mockService.getUsers() } returns getUserResponse()

        val result = runBlocking {
            remoteDataSource.getAllUser()
        }

        assertThat(result).isEqualTo(getUserList())
    }


    @Test(expected = IOException::class)
    fun `Quando servico jogar uma exception, tratar exception`() {

        coEvery { mockService.getUsers() } throws IOException()

        runBlocking {
            remoteDataSource.getAllUser()
        }
    }


    private fun getUserResponse() = listOf(
        UserResponse(
            id = 1,
            name = "Sandrine Spinka",
            imgUrl = "https://randomuser.me/api/portraits/men/1.jpg",
            userName = "Tod86"
        ),
        UserResponse(
            id = 1,
            name = "Sandrine Spinka",
            imgUrl = "https://randomuser.me/api/portraits/men/1.jpg",
            userName = "Tod86"
        )
    )

    private fun getUserList() = listOf(
        User(
            id = 1,
            name = "Sandrine Spinka",
            imgUrl = "https://randomuser.me/api/portraits/men/1.jpg",
            userName = "Tod86"
        ),
        User(
            id = 1,
            name = "Sandrine Spinka",
            imgUrl = "https://randomuser.me/api/portraits/men/1.jpg",
            userName = "Tod86"
        )
    )
}



