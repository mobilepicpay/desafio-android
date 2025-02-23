package com.picpay.desafio.android.presentation.ui.utils.compose

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertAny
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.picpay.desafio.android.presentation.ui.utils.FeedbackListener
import io.mockk.mockk
import io.mockk.verify
import org.junit.Rule
import org.junit.Test

private const val ERROR_FEEDBACK_TAG = "errorFeedbackTag"

class ErrorFeedbackComposableKtTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    // Prepare
    private val listener = mockk<FeedbackListener>(relaxed = true)

    private fun setContent() = composeTestRule.setContent {
        ErrorFeedbackComposable(
            modifier = Modifier.testTag(ERROR_FEEDBACK_TAG),
            listener = listener,
        )
    }

    @Test
    fun error_should_be_displayed() {
        // Given
        setContent()

        // Then
        composeTestRule.onNodeWithTag(ERROR_FEEDBACK_TAG)
            .assertIsDisplayed()
            .onChildren()
            .assertAny((hasTestTag(ERROR_TEXT_TAG)))
            .assertAny((hasTestTag(ERROR_BUTTON_TAG)))
    }

    @Test
    fun listener_should_be_called_when_button_clicked() {
        // Given
        setContent()

        // When
        composeTestRule.onNodeWithTag(ERROR_BUTTON_TAG)
            .assertHasClickAction()
            .performClick()

        // Then
        verify(exactly = 1) { listener.onButtonClicked() }
    }
}