package com.picpay.desafio.android

import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import com.picpay.desafio.android.data.network.api.services.PicPayService
import com.picpay.desafio.android.views.MainActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module


@ExperimentalCoroutinesApi
class MainActivityTest {
    companion object {
        private const val serverPort = 8088

        private val successResponse by lazy {
            val body =
                "[{\"id\":1001,\"name\":\"Eduardo Santos\",\"img\":\"https://randomuser.me/api/portraits/men/9.jpg\",\"username\":\"@eduardo.santos\"}]"

            MockResponse()
                .setResponseCode(200)
                .setBody(body)
        }

        private val errorResponse by lazy { MockResponse().setResponseCode(404) }
    }

    private val context = InstrumentationRegistry.getInstrumentation().targetContext
    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        server = MockWebServer()
        initModule()
    }

    //region TITLE
    @Test
    fun shouldDisplayTitle() {
        launchActivity<MainActivity>().apply {
            val expectedTitle = context.getString(R.string.title)

            moveToState(Lifecycle.State.RESUMED)

            onView(withText(expectedTitle)).check(matches(isDisplayed()))
        }
    }
    //endregion

    //region RECYCLER
    @Test
    fun testRecyclerViewWhenIsSuccessShouldDisplayListItem() {
        launchActivity<MainActivity>().apply {
            server.dispatcher = getDispatcher(true)
            server.start(serverPort)

            moveToState(Lifecycle.State.RESUMED)

            RecyclerViewMatchers.checkRecyclerViewItem(
                R.id.recyclerView,
                0,
                withText("Eduardo Santos")
            )
            //or
            // onView(withText("Eduardo Santos")).check(matches(isDisplayed()))
        }
    }

    @Test
    fun testRecyclerViewWhenIsSuccessShouldDisplayOneItem() {
        launchActivity<MainActivity>().apply {
            server.dispatcher = getDispatcher(true)
            server.start(serverPort)

            moveToState(Lifecycle.State.RESUMED)

            onActivity {
                val recycler = it.findViewById<RecyclerView>(R.id.recyclerView)
                val adapter = recycler.adapter
                assertEquals(1, adapter?.itemCount)
            }
        }
    }
    @Test
    fun testRecyclerViewWhenIsErrorShouldBeGone() {
        launchActivity<MainActivity>().apply {
            server.dispatcher = getDispatcher(false)
            server.start(serverPort)

            moveToState(Lifecycle.State.RESUMED)

            onActivity {
                val recycler = it.findViewById<RecyclerView>(R.id.recyclerView)
                assertEquals(View.GONE, recycler.visibility)
            }
            //or
            // onView(withText("Eduardo Santos")).check(matches(isNotDisplayed()))
        }
    }
    //endregion

    @After
    fun finish() {
        server.shutdown()
    }

    //region PRIVATE
    private fun getDispatcher(success: Boolean) = object : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            return when (request.path) {
                "/users" -> if (success) successResponse else errorResponse
                else -> errorResponse
            }
        }
    }

    private fun initModule() {
        val mockedModule = module {
            single(override = true) { PicPayService(url = "http://127.0.0.1:8088/") }
        }
        loadKoinModules(mockedModule)
    }
    //endregion
}