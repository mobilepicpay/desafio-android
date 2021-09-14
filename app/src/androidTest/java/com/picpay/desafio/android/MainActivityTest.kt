package com.picpay.desafio.android

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.google.gson.Gson
import com.picpay.desafio.android.uitest.base.BaseActivityTest
import com.picpay.desafio.android.uitest.matchers.RecyclerViewMatchers
import com.picpay.desafio.android.uitest.matchers.ToastMatcher
import com.picpay.desafio.android.uitest.utils.GenericIdlingResource
import org.junit.Test

class MainActivityTest : BaseActivityTest() {

    // Given that application is launched
    // When the screen appears
    // Then It should display the correct screen title
    @Test
    fun shouldDisplayTitle() {
        setMockSuccessResponse(path, successResponseBody)
        this.launchActivity().apply {
            val expectedTitle = context.getString(R.string.title)
            onView(withText(expectedTitle)).check(matches(isDisplayed()))
        }
    }

    // Given that application is launched and the screen is loaded
    // When the contact list appears
    // Then It should display the list item with the contact information
    @Test
    fun shouldDisplayListItem() {
        setMockSuccessResponse(path, successResponseBody)
        this.launchActivityWithIdling().apply {
            val user = mockedUser()
            val expectedListItemTexts = arrayOf(user.name, user.username)

            expectedListItemTexts.forEach {
                RecyclerViewMatchers.checkRecyclerViewItem(
                    R.id.recyclerView,
                    0,
                    withText(it)
                )
            }
        }
    }

    // Given that application is launched
    // When a network error occurs (like Http Status 404 : Page Not Found)
    // Then It should display a toast with an error message
    @Test
    fun shouldDisplayError() {
        setMockErrorResponse(path, 404)
        launchActivity().apply {
            ToastMatcher.isToastMessageDisplayed(R.string.error)
        }
    }

    private fun launchActivityWithIdling() =
        launchActivity().apply {
            onActivity { mainActivity ->
                super.idlingResource = GenericIdlingResource { mainActivity.isLoaded() }
            }
        }

    private fun launchActivity() =
        launchActivity<MainActivity>()

    companion object {
        private const val path = "/users"

        private const val successResponseBody =
            "[{\"id\":1001,\"name\":\"Eduardo Santos\",\"img\":\"https://randomuser.me/api/portraits/men/9.jpg\",\"username\":\"@eduardo.santos\"}]"

        private fun mockedUser(): User =
            Gson().fromJson(successResponseBody, Array<User>::class.java).first()
    }
}