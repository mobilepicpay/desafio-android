package com.picpay.desafio.android

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Description
import org.hamcrest.Matcher

object RecyclerViewMatchers {

    fun atPosition(
        position: Int,
        itemMatcher: Matcher<View>
    ) = object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
        override fun describeTo(description: Description?) {
            description?.appendText("has item at position $position: ")
            itemMatcher.describeTo(description)
        }

        override fun matchesSafely(item: RecyclerView?): Boolean {
            val viewHolder = item?.findViewHolderForAdapterPosition(position) ?: return false
            return itemMatcher.matches(viewHolder.itemView)
        }
    }

    fun checkRecyclerViewItem(resId: Int, position: Int, withMatcher: Matcher<View>) {
        Espresso.onView(ViewMatchers.withId(resId)).check(
            ViewAssertions.matches(
                atPosition(
                    position,
                    ViewMatchers.hasDescendant(withMatcher)
                )
            )
        )
    }
}