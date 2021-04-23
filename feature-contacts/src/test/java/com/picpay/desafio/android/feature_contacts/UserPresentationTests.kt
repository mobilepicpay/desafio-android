package com.picpay.desafio.android.feature_contacts

import com.google.common.truth.Truth.assertThat
import com.picpay.desafio.android.domain.models.User
import com.picpay.desafio.android.feature_contacts.models.UserPresentation
import org.junit.Test

class UserPresentationTests {

    @Test
    fun `should map contacts presentation`() {
        val users = User(
            id = 1001,
            username = "@leonardo",
            name = "Leonardo Cruz",
            img = "https://randomuser.me/api/portraits/men/9.jpg"
        )

        val expected = UserPresentation(
            name = "Leonardo Cruz",
            img = "https://randomuser.me/api/portraits/men/9.jpg",
            id = 1001,
            username = "@leonardo"
        )

        val presentation = UserPresentation(
            img = users.img,
            name = users.name,
            id = users.id,
            username = users.username
        )

        assertThat(expected).isEqualTo(presentation)
    }
}