package com.picpay.desafio.android.presentation.userlist

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.picpay.desafio.android.MainActivity
import com.picpay.desafio.android.presentation.userlist.UserListTestTag.USER_LAZY_COLUMN
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class UserListScreenTest {

    @get: Rule
    var hiltRule = HiltAndroidRule(this)

    @get: Rule
    var composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun should_display_list_of_users() {
        composeRule.onNodeWithTag(USER_LAZY_COLUMN).assertExists().assertIsDisplayed()

        UserListTestData.USERS.forEach { user ->
            composeRule.onNodeWithText(user.name).assertExists().assertIsDisplayed()
        }
    }

}
