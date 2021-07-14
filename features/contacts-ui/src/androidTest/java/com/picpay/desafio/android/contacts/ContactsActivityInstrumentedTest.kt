package com.picpay.desafio.android.contacts

import android.content.Intent
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.picpay.desafio.android.contact.R
import com.schibsted.spain.barista.assertion.BaristaListAssertions
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * @author Vitor Herrmann on 13/07/2021
 */
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4ClassRunner::class)
class ContactsActivityInstrumentedTest : ContactsModuleInstrumentedTest() {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var activityScenario: ActivityScenario<ContactsActivity>

    @Before
    fun setup() {
        loadTestModules()
        Intents.init()

        activityScenario = ActivityScenario.launch(
            Intent(
                getApplicationContext(),
                ContactsActivity::class.java
            )
        )
    }

    @Test
    fun test_something() {
        Espresso.onView(ViewMatchers.withId(R.id.title)).check(
            ViewAssertions.matches(ViewMatchers.withText("Contatos"))
        )

        BaristaListAssertions.assertDisplayedAtPosition(
            R.id.recyclerView,
            0,
            R.id.username,
            "Tod86"
        )
        BaristaListAssertions.assertDisplayedAtPosition(
            R.id.recyclerView,
            0,
            R.id.name,
            "Sandrine Spinka"
        )
        BaristaListAssertions.assertDisplayedAtPosition(
            R.id.recyclerView,
            1,
            R.id.username,
            "Constantin_Sawayn"
        )
        BaristaListAssertions.assertDisplayedAtPosition(
            R.id.recyclerView,
            1,
            R.id.name,
            "Carli Carroll2"
        )
    }

    @After
    fun cleanup() {
        activityScenario.close()
        Intents.release()
    }
}
