package com.picpay.desafio.android

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.jakewharton.espresso.OkHttp3IdlingResource
import com.picpay.desafio.android.ui.UserListActivity
import com.picpay.desafio.android.user.FileReader
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test


class MainActivityTest {

    private val server = MockWebServer()

    @Before
    fun setUp() {
        server.start(8080)
        IdlingRegistry.getInstance().register(
            OkHttp3IdlingResource.create(
                "okhttp",
                getClient()
            )
        )
    }

    fun getClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }


    @Test
    fun must_display_list_when_api_return_success() {
        server.enqueue(
            MockResponse().setResponseCode(200)
                .setBody(FileReader.readStringFromFile("success_response.json"))
        )

        launchActivity<UserListActivity>().apply {
            onView(withId(R.id.user_list_recyclerView)).check(matches(isDisplayed()))

        }
    }

    @After
    fun tearDown(){
        server.close()
    }

}