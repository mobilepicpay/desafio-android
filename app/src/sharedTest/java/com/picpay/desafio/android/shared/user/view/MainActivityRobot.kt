package com.picpay.desafio.android.shared.user.view

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import com.picpay.desafio.android.R
import com.picpay.desafio.android.shared.extensions.matchIsVisible
import com.picpay.desafio.android.user.view.MainActivity

fun startMainActivity(func: MainActivityRobot.() -> Unit): MainActivityRobot {
    val scenario = launchActivity<MainActivity>()
    return MainActivityRobot(scenario).apply(func)
}

class MainActivityRobot(private val scenario: ActivityScenario<MainActivity>) {

    fun clickElement() {
        //todo click
    }

    infix fun assert(func: Check.() -> Unit) {
        Check().apply(func)
    }

    inner class Check {
        fun matchProgressBarIsVisible() {
            matchIsVisible(R.id.progressBar)
        }
    }
}