package com.picpay.desafio.android.data.mappers

import com.picpay.desafio.android.data.model.UserResponse
import com.picpay.desafio.android.domain.User
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class UserResponseMapperTest {

    @Test
    fun `Deve converter os dados retornados da API, em dados Domain`() {
        val userResponse = UserResponse(
            id = 1,
            name = "Sandrine Spinka",
            imgUrl = "https://randomuser.me/api/portraits/men/1.jpg",
            userName = "Tod86"

        )

        val mapper = UserResponseMapper()

        val result = mapper.convertUserResponseToUser(userResponse)

        val expected = User(
            id = 1,
            name = "Sandrine Spinka",
            imgUrl = "https://randomuser.me/api/portraits/men/1.jpg",
            userName = "Tod86"
        )

        assertThat(result).isEqualTo(expected)

    }

}