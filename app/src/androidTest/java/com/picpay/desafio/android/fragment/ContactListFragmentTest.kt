package com.picpay.desafio.android.fragment

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.picpay.desafio.android.R
import com.picpay.desafio.android.presentation.fragment.ContactListFragment
import com.picpay.desafio.android.provider.MockResponseProvider
import com.picpay.desafio.android.server.MockWebServerRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ContactListFragmentTest {

    private lateinit var scenario: FragmentScenario<ContactListFragment>

    @get: Rule
    val mockWebServerRule = MockWebServerRule()

    @Before
    fun setup() {
        scenario = launchFragmentInContainer()
        scenario.moveToState(Lifecycle.State.STARTED)
        mockWebServerRule.server.enqueue(MockResponseProvider.usersMockResponse())
    }

    @After
    fun stop() {
        mockWebServerRule.server.shutdown()
    }

    @Test
    fun shouldDisplayTitleText() {
        onView(withId(R.id.title)).check(matches(isDisplayed()))
        onView(withId(R.id.title)).check(matches(withText(R.string.title)))
    }

}