package com.picpay.desafio.android

import androidx.lifecycle.Lifecycle
import androidx.room.Room
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import br.com.ribeiro.network.WebClient
import com.picpay.desafio.android.user.database.UserDataBase
import com.picpay.desafio.android.user.service.constants.ServiceConstant
import io.mockk.every
import io.mockk.mockkObject
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test

class MainActivityTest {
    private val server = MockWebServer()
    private val context = InstrumentationRegistry.getInstrumentation().targetContext
    private lateinit var dataBase: UserDataBase

    @Before
    fun setUp() {
        setupServerUrl()
        setupDatabase()
    }

    @After
    fun tearDown() {
        server.shutdown()
        dataBase.clearAllTables()
    }

    private fun setupServerUrl() {
        mockkObject(WebClient)
        every { WebClient.retrofit(ServiceConstant.URL) } returns WebClient.retrofit(
            server.url("/").toString()
        )
    }

    private fun setupDatabase() {
        dataBase = Room.inMemoryDatabaseBuilder(
            context,
            UserDataBase::class.java
        ).build()
        dataBase.clearAllTables()
    }

    @Test
    fun shouldDisplayTitle() {
        mockSucces()
        launchActivity<MainActivity>().apply {
            val expectedTitle = context.getString(R.string.title)
            moveToState(Lifecycle.State.RESUMED)
            onView(withText(expectedTitle)).check(matches(isDisplayed()))
        }
        server.shutdown()
    }


    @Test
    fun shouldDisplayListItem() {
        mockSucces()
        launchActivity<MainActivity>().apply {
            moveToState(Lifecycle.State.RESUMED)
            RecyclerViewMatchers.atPosition(0, withText("@eduardo.santos"))
        }
        server.shutdown()
    }

    @Test
    fun shouldDisplayError() {
        mockError()
        launchActivity<MainActivity>().apply {
            val expectedError = context.getString(R.string.error)
            onView(withText(expectedError))
        }
        server.shutdown()

    }

    private fun mockSucces() {
        val responseCodeSuccess = 200
        val body =
            "[{\"id\":1001,\"name\":\"Eduardo Santos\",\"img\":\"https://randomuser.me/api/portraits/men/9.jpg\",\"username\":\"@eduardo.santos\"}]"
        addRequest(responseCodeSuccess, body)
    }

    private fun mockError() {
        val responseCodeError = 404
        addRequest(responseCodeError)
    }

    private fun addRequest(statusCode: Int, body: String = "") {
        server.enqueue(
            MockResponse().apply {
                setResponseCode(statusCode)
                setBody(body)
            }
        )
    }
}