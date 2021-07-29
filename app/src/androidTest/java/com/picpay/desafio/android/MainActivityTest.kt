package com.picpay.desafio.android

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import com.jakewharton.espresso.OkHttp3IdlingResource
import com.picpay.desafio.android.data.source.local.UserDao
import com.picpay.desafio.android.ui.MainActivity
import com.picpay.desafio.android.util.DataBindingIdlingResource
import com.picpay.desafio.android.util.FileReader
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.lang.Thread.sleep
import javax.inject.Inject


@HiltAndroidTest
class MainActivityTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private val dataBindingIdlingResource = DataBindingIdlingResource()

    private lateinit var server: MockWebServer


    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Inject
    lateinit var okHttp3IdlingResource: OkHttp3IdlingResource

    @Before
    fun setUp() {
        hiltRule.inject()
        IdlingRegistry.getInstance().register(dataBindingIdlingResource)
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        activityScenario.moveToState(Lifecycle.State.RESUMED)
        dataBindingIdlingResource.monitorActivity(activityScenario)
        server = MockWebServer()
        server.start(8080)
        IdlingRegistry.getInstance().register(okHttp3IdlingResource)
    }


    @After
    fun tearDown() =
        server.shutdown()

    @Test
    fun shouldDisplayTitle() {
        server.enqueue(
            MockResponse().setResponseCode(200).setBody(FileReader.readStringFromFile("users.json"))
        )
        val expectedTitle = context.getString(R.string.title)
        onView(withText(expectedTitle)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldDisplayListItem() {
        server.enqueue(
            MockResponse().setResponseCode(200).setBody(FileReader.readStringFromFile("users.json"))
        )
        sleep(2000)
        RecyclerViewMatchers.checkRecyclerViewItem(
            R.id.recyclerView,
            0,
            withText("Sandrine Spinka")
        )
    }

    @Test
    fun shouldDisplayError() {
        server.shutdown()
        sleep(2000)
        onView(withId(R.id.llError)).check(matches(isDisplayed()))
    }
}