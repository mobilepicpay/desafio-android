package com.picpay.desafio.android

import androidx.fragment.app.testing.launchFragment
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.launchActivity
import androidx.test.platform.app.InstrumentationRegistry
import com.picpay.android.user.presentation.UserFragment
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.Test

class MainActivityTest {

    private val server = MockWebServer()

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun shouldDisplayTitle() {

        val scenario = launchFragment<UserFragment>(themeResId = R.style.AppTheme)
        scenario.moveToState(Lifecycle.State.RESUMED)

        val expectedTitle = context.getString(R.string.title)

    }

    @Test
    fun shouldDisplayListItem() {
        server.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return when (request.path) {
                    "/users" -> successResponse
                    else -> errorResponse
                }
            }
        }

        server.start(serverPort)

        val scenario = launchFragment<UserFragment>(themeResId = R.style.AppTheme)
        scenario.moveToState(Lifecycle.State.RESUMED)

        scenario.apply {
            val text = ""
        }

//        launchActivity<MainActivity>().apply {
//            // TODO("validate if list displays items returned by server")
//        }

        server.close()
    }

    companion object {
        private const val serverPort = 8080

        private val successResponse by lazy {
            val body =
                "[{\"id\":1001,\"name\":\"Eduardo Santos\",\"img\":\"https://randomuser.me/api/portraits/men/9.jpg\",\"username\":\"@eduardo.santos\"}]"

            MockResponse()
                .setResponseCode(200)
                .setBody(body)
        }

        private val errorResponse by lazy { MockResponse().setResponseCode(404) }

    }
}