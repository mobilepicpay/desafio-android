package com.picpay.desafio.android.presentation.fragments

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.picpay.desafio.android.R
import com.picpay.desafio.android.data.server.MockWebServerRule
import com.picpay.desafio.android.providers.MockAndroidContactProvider
import com.picpay.desafio.android.providers.MockResponseProvider
import com.picpay.desafio.android.providers.RecyclerViewMatcherProvider
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ContactFragmentTest {

    private lateinit var scenario: FragmentScenario<ContactFragment>

    @get:Rule
    val mockWebServerRule = MockWebServerRule()

    @Before
    fun setup() {
        mockWebServerRule.server.enqueue(MockResponseProvider.usersMockResponse())
        scenario = launchFragmentInContainer()
        scenario.moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun shouldDisplayTitle() {
        onView(withId(R.id.title)).check(matches(isDisplayed()))
        onView(withText(R.string.title)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldDisplayListItemText() {
        RecyclerViewMatcherProvider.checkRecyclerViewItem(
            R.id.recyclerView,
            position = 0,
            withText(MockAndroidContactProvider.mockedContact().name)
        )
    }

}