package com.picpay.desafio.userlist.utils

import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import org.hamcrest.Matcher
import android.content.pm.ActivityInfo
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.view.ViewGroup
import androidx.test.runner.lifecycle.Stage

class OrientationChangeAction(private val orientation: Int) : ViewAction {

    companion object {
        fun orientationLandscape(): ViewAction =
            OrientationChangeAction(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)

        fun orientationPortrait(): ViewAction =
            OrientationChangeAction(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
    }

    override fun getDescription(): String = "change orientation to $orientation"

    override fun getConstraints(): Matcher<View> = isRoot()

    override fun perform(uiController: UiController, view: View) {
        uiController.loopMainThreadUntilIdle()
        var activity = getActivity(view.context)
        if (activity == null && view is ViewGroup) {
            val c = view.childCount
            var i = 0
            while (i < c && activity == null) {
                activity = getActivity(view.getChildAt(i).context)
                ++i
            }
        }
        activity!!.requestedOrientation = orientation
    }

    private fun getActivity(context: Context): Activity? {
        var context = context
        while (context is ContextWrapper) {
            if (context is Activity) {
                return context
            }
            context = (context as ContextWrapper).baseContext
        }
        return null
    }

}