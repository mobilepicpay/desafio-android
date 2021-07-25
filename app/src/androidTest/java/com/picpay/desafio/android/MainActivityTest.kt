package com.picpay.desafio.android

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import com.jakewharton.espresso.OkHttp3IdlingResource
import com.picpay.desafio.android.ui.MainActivity
import com.picpay.desafio.android.util.DataBindingIdlingResource
import com.picpay.desafio.android.util.FileReader
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject


@HiltAndroidTest
class MainActivityTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private val dataBindingIdlingResource = DataBindingIdlingResource()

    private lateinit var server: MockWebServer


    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Inject
    lateinit var okHttpClient: OkHttpClient

    @Before
    fun setUp() {
        hiltRule.inject()
        IdlingRegistry.getInstance().register(dataBindingIdlingResource)
        val resource: IdlingResource = OkHttp3IdlingResource.create("okhttp", okHttpClient)
        IdlingRegistry.getInstance().register(resource)
        server = MockWebServer()
        server.start(8080)
    }


    @After
    fun tearDown() =
        server.shutdown()

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
        server.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse()
                    .setResponseCode(200)
                    .setBody(FileReader.readStringFromFile("users.json"))
            }
        }
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        dataBindingIdlingResource.monitorActivity(activityScenario)

        RecyclerViewMatchers.checkRecyclerViewItem(
            R.id.recyclerView,
            0,
            withText("Sandrine Spinka")
        )

    }

}