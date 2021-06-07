package com.picpay.desafio.android.features

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import com.picpay.desafio.android.R
import com.picpay.desafio.android.utils.BaseRobot
import com.picpay.desafio.android.utils.withRecyclerView
import org.hamcrest.CoreMatchers

fun main(func: MainActivityRobot.() -> Unit) = MainActivityRobot().apply { func() }

class MainActivityRobot : BaseRobot() {

    fun assertLayout() {
        isVisible(R.id.title)
    }

    fun assertListItemLayout() {
        isVisibleAsDescendant(R.id.recyclerView, R.id.picture)
        isVisibleAsDescendant(R.id.recyclerView, R.id.name)
        isVisibleAsDescendant(R.id.recyclerView, R.id.username)
        isVisibleAsDescendant(R.id.recyclerView, R.id.username)
    }

    fun checkDisplayTitle() {
        val expectedTitle = context.getString(R.string.title)
        isVisibleWithText(expectedTitle)
    }

    fun checkRecyclerViewVisible() {
        isVisible(R.id.recyclerView)
    }

    fun checkRecyclerViewGone() {
        isNotVisible(R.id.recyclerView)
    }

    fun checkProgressBarVisible() {
        isVisible(R.id.user_list_progress_bar)
    }

    fun checkProgressBarGone() {
        isNotVisible(R.id.user_list_progress_bar)
    }

    fun clickAtPosition(position: Int) {
        onView(
            CoreMatchers.allOf(
                withRecyclerView(R.id.recyclerView)
                    .atPositionOnView(
                        position,
                        R.id.container
                    )
            )
        ).perform(ViewActions.click())
    }

}