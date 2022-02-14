package com.picpay.desafio.userlist.utils.rules

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class AppRunner: AndroidJUnitRunner() {

    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, AppAndroidTest::class.java.name, context)
    }
}