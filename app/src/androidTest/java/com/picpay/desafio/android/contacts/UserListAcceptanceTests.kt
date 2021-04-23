package com.picpay.desafio.android.contacts

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.picpay.desafio.android.R
import com.picpay.desafio.android.feature_contacts.presentation.UserActivity
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserListAcceptanceTests {
    lateinit var server: MockWebServer

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun beforeEachTest() {
        server = MockWebServer().apply {
            start(port = 4242)
        }
    }

    @After
    fun afterEachTest() {
        server.shutdown()
    }

    @Test
    fun givenSuccessfulResponse_shouldDisplayFacts() {
        val payload =
            """
            [
              {
               "id":1001,
               "name":"Eduardo Santos",
               "img":"https://randomuser.me/api/portraits/men/9.jpg",
               "username":"@eduardo.santos"
               }, 
               {
               "id":1002,
               "name":"Marina Coelho",
               "img":"https://randomuser.me/api/portraits/women/37.jpg",
               "username":"@marina.coelho"
               }
            ]
            """.trimIndent()

        server.enqueue(
            MockResponse().setResponseCode(200).setBody(payload)
        )

        //scenarioLauncher<FactsActivity>().run {
        //    onResume {
        //        factsListChecks {
        //            loadingIndicator shouldBe HIDDEN
        //            errorState shouldBe HIDDEN
        //            "Chuck Norris can divide by zero" shouldBe DISPLAYED
        //        }
        //    }
        //}

        launchActivity<UserActivity>().run {
            val expectedTitle = context.getString(R.string.title)

            moveToState(Lifecycle.State.RESUMED)

            onView(withText(expectedTitle)).check(matches(isDisplayed()))
        }

    }
}