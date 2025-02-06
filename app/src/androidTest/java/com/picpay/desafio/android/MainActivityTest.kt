package com.picpay.desafio.android

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.picpay.desafio.android.presentation.MainActivity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@MediumTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Before
    fun setup() {
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun givenActivityLaunched_whenMainActivityStarts_thenDisplayRecyclerView() {
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
    }

    @Test
    fun givenActivityLaunched_whenMainActivityStarts_thenDisplayProgressBarInitially() {
        onView(withId(R.id.shimmerLayout)).check(matches(isDisplayed()))
    }
}
