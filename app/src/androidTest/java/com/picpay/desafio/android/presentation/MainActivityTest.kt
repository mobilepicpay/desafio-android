package com.picpay.desafio.android.presentation

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.picpay.desafio.android.ApiTestRunner.Companion.MOCK_WEB_SERVER_PORT
import com.picpay.desafio.android.EspressoIdlingResourceRule
import com.picpay.desafio.android.R
import com.picpay.desafio.android.RecyclerViewMatchers.checkRecyclerViewItem
import com.picpay.desafio.android.core.EspressoIdlingResource
import com.picpay.desafio.android.data.source.local.AppDatabase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class MainActivityTest : KoinTest {

    private val server = MockWebServer()

    @get:Rule
    val espressoIdlingResourceRule = EspressoIdlingResourceRule()

    private lateinit var webServer: MockWebServer

    @Before
    @Throws(Exception::class)
    fun setup() {
        webServer = MockWebServer()
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        webServer.shutdown()
        val db = getKoin().get<AppDatabase>()
        db.clearAllTables()
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun shouldDisplayTitle() {
        launchActivity<MainActivity>().apply {
            val expectedTitle = "Contatos"

            moveToState(Lifecycle.State.RESUMED)

            onView(withText(expectedTitle)).check(matches(isDisplayed()))
        }
    }

    @Test
    fun shouldDisplayRefreshButton() = runTest {
        server.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse = errorResponse
        }
        server.start(MOCK_WEB_SERVER_PORT)

        launchActivity<MainActivity>().apply {
            val expectedButtonName = "Recarregar"
            moveToState(Lifecycle.State.RESUMED)

            onView(withText(expectedButtonName)).check(matches(isDisplayed()))
        }
        server.close()
    }

    @Test
    fun shouldDisplayItemList() = runTest {
        server.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse = successResponse
        }
        server.start(MOCK_WEB_SERVER_PORT)

        launchActivity<MainActivity>().apply {
            moveToState(Lifecycle.State.RESUMED)

            checkRecyclerViewItem(
                R.id.recyclerView,
                0,
                withText("Eduardo Santos")
            )
            checkRecyclerViewItem(
                R.id.recyclerView,
                0,
                withText("@eduardo.santos")
            )
        }
        server.close()
    }

    companion object {

        private val successResponse by lazy {
            val body =
                "[{\"id\":1001,\"name\":\"Eduardo Santos\"," +
                    "\"img\":\"https://randomuser.me/api/portraits/men/9.jpg\"," +
                    "\"username\":\"@eduardo.santos\"}]"

            MockResponse().setResponseCode(200).setBody(body)
        }

        private val errorResponse by lazy { MockResponse().setResponseCode(404) }
    }
}
