package com.picpay.desafio.android.utils

import android.view.View
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.CoreMatchers
import org.hamcrest.Description
import org.hamcrest.Matcher

object ViewActionUtils {
    fun hideCursorFromEditText(): ViewAction {
        return object : ViewAction {
            override fun getDescription(): String {
                return "Remove o cursor do editText"
            }

            override fun getConstraints(): Matcher<View> {
                return CoreMatchers.allOf(
                    ViewMatchers.isAssignableFrom(EditText::class.java),
                    ViewMatchers.isDisplayed()
                )
            }

            override fun perform(uiController: UiController, view: View) {
                if (view is EditText) {
                    view.isCursorVisible = false
                }
            }
        }
    }

    fun withViewAtPosition(
        position: Int,
        itemMatcher: Matcher<View?>
    ): Matcher<View?>? {
        return object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description?) {
                itemMatcher.describeTo(description)
            }

            override fun matchesSafely(recyclerView: RecyclerView): Boolean {
                val viewHolder =
                    recyclerView.findViewHolderForAdapterPosition(position)
                return viewHolder != null && itemMatcher.matches(viewHolder.itemView)
            }
        }
    }

    fun waitFor(delay: Long = 1000): ViewAction {
        return object : ViewAction {
            override fun perform(uiController: UiController?, view: View?) {
                uiController?.loopMainThreadForAtLeast(delay)
            }

            override fun getConstraints(): Matcher<View> {
                return ViewMatchers.isAssignableFrom(View::class.java)
            }

            override fun getDescription(): String {
                return "espera por ${delay}ms"
            }
        }
    }
}