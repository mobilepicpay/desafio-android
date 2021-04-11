package com.picpay.desafio.android.feature.home

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.picpay.desafio.android.espresso.NestedScrollToAction

class HomeBot {

    init {
        checkScreen()
    }

    private fun checkScreen() = apply {
        onView(withId(R.id.title)).check(matches(withText("Contatos")))
    }

    fun checkItemIsDisplayedByText(text: String) = apply {
        onView(withText(text))
            .perform(nestedScrollTo())
            .check(matches(isDisplayed()))
    }

    private fun nestedScrollTo(): ViewAction {
        return ViewActions.actionWithAssertions(NestedScrollToAction())
    }
}