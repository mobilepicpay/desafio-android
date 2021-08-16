package com.picpay.desafio.android.helper

import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.picpay.desafio.android.helper.matchers.RecyclerViewItemCountAssertion

fun checkRecyclerViewHasItems(@IdRes recyclerViewId: Int, expected: Int) {
    onView(withId(recyclerViewId)).check(RecyclerViewItemCountAssertion(expected))
}

fun matchIsVisible(@IdRes id: Int) {
    onView(withId(id)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
}