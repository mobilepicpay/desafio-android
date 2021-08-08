package com.picpay.desafio.android

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import com.picpay.android.user.R
import com.picpay.android.user.presentation.UserFragment
import org.junit.Test

class MainActivityTest {

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun shouldDisplayListItem() {

        launchFragmentInContainer<UserFragment>(themeResId = R.style.AppTheme).apply {
            moveToState(Lifecycle.State.CREATED)
        }

        RecyclerViewMatchers.checkRecyclerViewItem(R.id.userList, 0, withText(""))
    }

}