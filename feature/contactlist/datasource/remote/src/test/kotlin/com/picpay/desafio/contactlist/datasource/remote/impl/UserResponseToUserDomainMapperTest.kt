package com.picpay.desafio.contactlist.datasource.remote.impl

import com.google.common.truth.Truth
import com.picpay.desafio.feature.contactlist.usecase.UserDomain
import org.junit.Test

class UserResponseToUserDomainMapperTest {

    private val mapper = UserResponseToUserDomainMapper()

    @Test
    fun map_receiveUserResponse_expectedUserDomain() {
        val expected = UserDomain(
            img = "img-url",
            name = "name",
            id = 1,
            username = "username"
        )

        val actual = mapper.mapToDomain(
            UserResponse(
                img = "img-url",
                name = "name",
                id = 1,
                username = "username"
            )
        )

        Truth.assertThat(actual).isEqualTo(expected)
    }
}