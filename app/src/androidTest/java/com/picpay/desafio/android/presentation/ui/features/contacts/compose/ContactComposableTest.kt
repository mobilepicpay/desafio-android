package com.picpay.desafio.android.presentation.ui.features.contacts.compose

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.picpay.desafio.android.domain.features.contacts.model.Contact
import org.junit.Rule
import org.junit.Test

class ContactComposableTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun assert_contact_properties_are_displayed_correctly() {
        // Given
        val contact = Contact(
            id = 1,
            name = "Rodrigo Murta",
            username = "rodrigo.mmurta",
            image = "https://randomuser.me/api/portraits/men/1.jpg"
        )

        // When
        composeTestRule.setContent {
            ContactComposable(
                modifier = Modifier,
                contact = contact
            )
        }

        // Then
        composeTestRule.onNodeWithTag(IMAGE_TAG)
            .assertIsDisplayed()

        composeTestRule.onNodeWithTag(USERNAME_TAG)
            .assertIsDisplayed()
            .assertTextEquals("rodrigo.mmurta")

        composeTestRule.onNodeWithTag(NAME_TAG)
            .assertIsDisplayed()
            .assertTextEquals("Rodrigo Murta")
    }
}