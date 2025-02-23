package com.picpay.desafio.android.presentation.ui.utils.compose

import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.picpay.desafio.android.domain.utils.ErrorInformation
import com.picpay.desafio.android.domain.utils.State
import com.picpay.desafio.android.presentation.ui.features.contacts.compose.CACHE_TAG
import com.picpay.desafio.android.presentation.ui.features.contacts.compose.ERROR_TAG
import com.picpay.desafio.android.presentation.ui.features.contacts.compose.LOADING_IMAGE_TAG
import com.picpay.desafio.android.presentation.ui.features.contacts.compose.LOADING_TAG
import com.picpay.desafio.android.presentation.ui.utils.FeedbackListener
import io.mockk.mockk
import org.junit.Rule
import org.junit.Test

private const val SUCCESS_TAG = "successTag"

class ScreenComposableTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    // Prepare
    private val listener = mockk<FeedbackListener>(relaxed = true)

    private fun setCompose(screenState: State<Any>) {
        composeTestRule.setContent {
            ScreenComposable(
                screenState = screenState,
                listener = listener,
                toolbar = {},
                successContent = {
                    Text(text = "Success Content", modifier = Modifier.testTag(SUCCESS_TAG))
                }
            )
        }
    }

    @Test
    fun screenComposable_should_display_loading_content_when_state_is_Loading() {
        // Given
        val state = State.Loading

        // When
        setCompose(state)

        // Then
        composeTestRule.onNodeWithTag(LOADING_TAG).assertIsDisplayed()
        composeTestRule.onNodeWithTag(LOADING_IMAGE_TAG).assertIsDisplayed()

        composeTestRule.onNodeWithTag(ERROR_TAG).assertDoesNotExist()
        composeTestRule.onNodeWithTag(CACHE_TAG).assertDoesNotExist()
        composeTestRule.onNodeWithTag(SUCCESS_TAG).assertDoesNotExist()
    }

    @Test
    fun screenComposable_should_display_error_content_when_state_is_Error() {
        // Given
        val state = State.Error(
            error = ErrorInformation(
                message = "Ocorreu um erro, tente novamente."
            )
        )

        // When
        setCompose(state)

        // Then
        composeTestRule.onNodeWithTag(ERROR_TAG).assertIsDisplayed()

        composeTestRule.onNodeWithTag(LOADING_TAG).assertDoesNotExist()
        composeTestRule.onNodeWithTag(CACHE_TAG).assertDoesNotExist()
        composeTestRule.onNodeWithTag(SUCCESS_TAG).assertDoesNotExist()
    }

    @Test
    fun screenComposable_should_display_cache_alert_and_success_content_when_state_is_ErrorWithCache() {
        // Given
        val state = State.ErrorWithCache<Any>(
            error = ErrorInformation(
                message = "Não foi possível conectar ao servidor. Exibindo dados em cache."
            )
        )

        // When
        setCompose(state)

        // Then
        composeTestRule.onNodeWithTag(CACHE_TAG).assertIsDisplayed()
        composeTestRule.onNodeWithTag(SUCCESS_TAG).assertIsDisplayed()

        composeTestRule.onNodeWithTag(ERROR_TAG).assertDoesNotExist()
        composeTestRule.onNodeWithTag(LOADING_TAG).assertDoesNotExist()
    }

    @Test
    fun screenComposable_should_display_success_content_when_state_is_Success() {
        // Given
        val state = State.Success(Any())

        // When
        setCompose(state)

        // Then
        composeTestRule.onNodeWithTag(SUCCESS_TAG).assertIsDisplayed()

        composeTestRule.onNodeWithTag(LOADING_TAG).assertDoesNotExist()
        composeTestRule.onNodeWithTag(ERROR_TAG).assertDoesNotExist()
        composeTestRule.onNodeWithTag(CACHE_TAG).assertDoesNotExist()

    }
}