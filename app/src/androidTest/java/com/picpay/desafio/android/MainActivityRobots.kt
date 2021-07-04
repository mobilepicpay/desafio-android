package com.picpay.desafio.android

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.picpay.desafio.android.data.remote.util.BaseURL
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.not

class MainActivityPrepare {
    private val server = MockWebServer()

    fun mockSuccess() {
        BaseURL.isTesting = true
        server.enqueue(successResponse)

        server.start(serverPort)
    }

    fun closeServer() {
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

class MainActivityValidate() {
    fun verifyFirstItemRecycler(name: String) {
        RecyclerViewMatchers.checkRecyclerViewItem(
            R.id.recyclerView,
            0,
            ViewMatchers.withText(name)
        )
    }

    fun checkToast(text: String, decorView: View) {
        onView(withText(text)).inRoot(
            withDecorView(
                not(
                    `is`(
                        decorView
                    )
                )
            )
        ).check(
            matches(
                isDisplayed()
            )
        )
    }
}