package com.picpay.desafio.android.espresso

import android.view.View
import android.widget.HorizontalScrollView
import android.widget.ScrollView
import androidx.core.widget.NestedScrollView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ScrollToAction
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.anyOf


class NestedScrollToAction : ViewAction by ScrollToAction() {
    override fun getConstraints(): Matcher<View?>? {
        return allOf(
            withEffectiveVisibility(Visibility.VISIBLE), isDescendantOfA(
                anyOf(
                    isAssignableFrom(ScrollView::class.java),
                    isAssignableFrom(HorizontalScrollView::class.java),
                    isAssignableFrom(NestedScrollView::class.java)
                )
            )
        )
    }
}