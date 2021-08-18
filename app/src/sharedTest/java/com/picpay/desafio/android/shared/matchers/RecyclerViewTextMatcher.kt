package com.picpay.desafio.android.shared.matchers

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher

fun withIdAtPosition(
    position: Int,
    viewId: Int,
    matcher: Matcher<View?>
): Matcher<View?> {
    return object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {
        override fun describeTo(description: Description) {
            description.appendText("has item at position $position: ")
            matcher.describeTo(description)
        }

        override fun matchesSafely(view: RecyclerView?): Boolean {
            val viewHolder = view?.findViewHolderForAdapterPosition(position) ?: return false
            return matcher.matches(viewHolder.itemView.findViewById(viewId))
        }
    }
}