package com.picpay.desafio.android

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import com.picpay.desafio.android.ui.activity.MainActivity
import org.junit.Test

class MainActivityTest {
    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    fun prepare(func: MainActivityPrepare.() -> Unit) = MainActivityPrepare().apply { func() }
    fun validate(func: MainActivityValidate.() -> Unit) = MainActivityValidate().apply { func() }

    @Test
    fun shouldDisplayTitle() {
        launchActivity<MainActivity>().apply {
            val expectedTitle = context.getString(R.string.title)

            moveToState(Lifecycle.State.RESUMED)

            onView(withText(expectedTitle)).check(matches(isDisplayed()))
        }
    }

    @Test
    fun shouldDisplayListItem() {
        prepare {
            mockSuccess()
        }

        launchActivity<MainActivity>().apply {
            moveToState(Lifecycle.State.RESUMED)

            validate {
                verifyFirstItemRecycler("Eduardo Santos")
            }

            prepare {
                closeServer()
            }
        }
    }
}