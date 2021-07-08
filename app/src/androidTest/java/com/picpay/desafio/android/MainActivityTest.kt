package com.picpay.desafio.android

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.desafio.picpay.android.testcoreutil.MockServerRule
import com.picpay.desafio.android.RecyclerViewMatchers.checkRecyclerViewItem
import com.picpay.desafio.android.ui.view.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    val serverRule = MockServerRule()

    private fun mockSuccessResponse() {
        serverRule.mockResponse("/users", "users/mock_list_user.json")
    }

    @Before
    fun setUp() {
        mockSuccessResponse()
        launchActivity<MainActivity>()
    }

    @Test
    fun shouldDisplayListItem() {
        onView(withText("Contatos")).check(matches(isDisplayed()))
        checkRecyclerViewItem(R.id.rvUsers, 0, withText("Tom"))
        checkRecyclerViewItem(R.id.rvUsers, 1, withText("Maria"))
        checkRecyclerViewItem(R.id.rvUsers, 2, withText("Robert"))
    }
}