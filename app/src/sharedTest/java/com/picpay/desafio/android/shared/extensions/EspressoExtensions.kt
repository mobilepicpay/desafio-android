package com.picpay.desafio.android.shared.extensions

import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.picpay.desafio.android.shared.matchers.RecyclerViewItemCountAssertion
import com.picpay.desafio.android.shared.matchers.withIdAtPosition

fun click(@IdRes viewId: Int) {
    onView(withId(viewId)).perform(ViewActions.click())
}

fun checkIsDisplayedByText(text: String) {
    onView(withText(text)).check(matches(ViewMatchers.isDisplayed()))
}

fun checkRecyclerViewHasItems(@IdRes recyclerViewId: Int, expected: Int) {
    onView(withId(recyclerViewId)).check(RecyclerViewItemCountAssertion(expected))
}

fun matchesTextAtPosition(
    @IdRes recyclerViewId: Int,
    @IdRes viewId: Int,
    position: Int,
    expected: String
) {
    onView(withId(recyclerViewId))
        .check(matches(withIdAtPosition(position, viewId, withText(expected))))
}

fun matchIsVisible(@IdRes id: Int) {
    onView(withId(id)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
}