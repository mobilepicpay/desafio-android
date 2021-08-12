package com.picpay.desafio.android.data.api.mappers

import com.picpay.desafio.android.data.api.responses.UserResponse
import com.picpay.desafio.android.models.User
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

class UserRemoteMapperTest {

    private lateinit var userRemoteMapper: RemoteMapper<UserResponse, User>

    @Before
    fun setup() {
        userRemoteMapper = UserRemoteMapper()
    }

    @Test
    fun `fromResponseToModel, when a response is passed, it is mapped to the domain type`() {
        val userResponse = UserResponse("image url", "John", 1, "John10")
        val expectedReturn = userResponse.run {
            User(id, name, image, username)
        }

        val user = userRemoteMapper.fromResponseToModel(userResponse)
        assertThat(user, `is`(expectedReturn))
    }
}
