package com.picpay.desafio.android.main

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.content.Context.ACTIVITY_SERVICE
import androidx.test.core.app.ApplicationProvider

const val TIMEOUT = 5000L
const val CHECK_INTERVAL = 100L

inline fun <reified Type : Activity> waitUntilActivityVisible() {
    val startTime = System.currentTimeMillis()
    while (!isVisible<Type>()) {
        Thread.sleep(CHECK_INTERVAL)
        if (System.currentTimeMillis() - startTime >= TIMEOUT) {
            throw AssertionError("Activity ${Type::class.java.simpleName} not visible after $TIMEOUT milliseconds")
        }
    }
}

inline fun <reified Type : Activity> isVisible(): Boolean {
    val am = ApplicationProvider.getApplicationContext<Context>()
        .getSystemService(ACTIVITY_SERVICE) as ActivityManager
    val visibleActivityName = am.appTasks[0].taskInfo.baseActivity?.className
    return visibleActivityName == Type::class.java.name
}