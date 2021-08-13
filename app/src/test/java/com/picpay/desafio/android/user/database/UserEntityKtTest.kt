package com.picpay.desafio.android.user.database

import com.picpay.desafio.android.user.domain.UserDomain
import org.junit.Test
import kotlin.test.assertEquals

class UserEntityKtTest {
    @Test
    fun `Should map user response to user domain correctly`() {
        val actual = listOf(
            UserEntity(imageUrl = "img", name = "name", id = 1, userName = "username")
        )

        val expected = listOf(
            UserDomain(imageUrl = "img", name = "name", id = 1, userName = "username")
        )

        assertEquals(expected, actual.toDomain())
    }
}