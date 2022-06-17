package com.picpay.desafio.android

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get: Rule
    val rule = activityScenarioRule<MainActivity>()

    @Test
    fun shouldDisplayTitle() {
        onView(withId(R.id.title)).check(matches(isDisplayed()))
        onView(ViewMatchers.withText(R.string.title)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldDisplayFragmentContainer() {
        onView(withId(R.id.fcvContainer)).check(matches(isDisplayed()))
    }

}