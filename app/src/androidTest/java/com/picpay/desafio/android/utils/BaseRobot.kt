package com.picpay.desafio.android.utils

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.pressBack
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.isPlatformPopup
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.CoreMatchers.not

open class BaseRobot() {

    fun isVisibleWithText(text: String) {
        onView(withText(text))
            .check(matches(isDisplayed()))
    }

    fun clickOnView(viewId: Int) {
        val perform = onView(withId(viewId))
            .perform(ViewActionUtils.waitFor(), click())
    }

    fun clickOnViewContainingText(text: String) {
        onView(withText(containsString(text)))
            .inRoot(isPlatformPopup())
            .perform(click())
    }

    fun isVisible(viewId: Int) {
        onView(withId(viewId))
            .check(matches(isDisplayed()))
    }

    fun isNotVisible(viewId: Int) {
        onView(withId(viewId))
            .check(matches(not(isDisplayed())))
    }

    fun doesNotExist(viewId: Int) {
        onView(withId(viewId)).check(doesNotExist())
    }

    fun isVisibleAsDescendant(rootView: Int, descendantView: Int) {
        onView(withId(rootView)).check(
            matches(
                allOf(
                    hasDescendant(
                        withId(
                            descendantView
                        )
                    ),
                    isDisplayed()
                )
            )
        )
    }

    fun fillField(viewId: Int, text: String) {
        onView(withId(viewId))
            .perform(
                clearText()
                , typeText(text)
            )
            .perform(closeSoftKeyboard())

    }

    fun hasText(viewId: Int, text: String) {
        onView(withId(viewId))
            .check(matches(withText((text))))
    }

    fun hideKeyboard(viewId: Int) {
        onView(withId(viewId)).perform(closeSoftKeyboard())
    }

    fun pressBackButton() {
        pressBack()
    }

    companion object {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
    }
}