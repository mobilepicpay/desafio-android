package com.picpay.desafio.android.presentation.ui.utils.compose

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.picpay.desafio.android.domain.utils.ErrorInformation
import org.junit.Rule
import org.junit.Test

class CacheAlertComposableTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun assert_cache_alert_is_displayed_correctly() {
        // Given
        val errorMessage = "Ocorreu um erro. Exibindo dados em cache."

        // When
        composeTestRule.setContent {
            CacheAlertComposable(
                error = ErrorInformation(
                    message = errorMessage
                )
            )
        }

        // Then
        composeTestRule.onNodeWithTag(TEXT_TAG).assertIsDisplayed()
        composeTestRule.onNodeWithTag(TEXT_TAG).assertTextEquals(errorMessage)
    }
}