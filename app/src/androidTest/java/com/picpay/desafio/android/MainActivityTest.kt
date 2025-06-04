package com.picpay.desafio.android

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.picpay.desafio.android.di.testModule
import com.picpay.desafio.android.ui.MainActivity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest


@RunWith(AndroidJUnit4::class)
class MainActivityTest : KoinTest {

    @Before
    fun setup() {
        stopKoin()
        startKoin {
            androidContext(ApplicationProvider.getApplicationContext())
            modules(testModule)
        }
    }

    @Test
    fun testRecyclerViewLoadsUsers() {
        ActivityScenario.launch(MainActivity::class.java).use {
            Thread.sleep(2000)

            onView(withId(R.id.recyclerView))
                .check(matches(isDisplayed()))

            onView(withText("Maria Silva"))
                .check(matches(isDisplayed()))
        }
    }
}