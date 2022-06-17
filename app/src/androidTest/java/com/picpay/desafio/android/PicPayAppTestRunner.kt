package com.picpay.desafio.android

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class PicPayAppTestRunner : AndroidJUnitRunner() {

    override fun newApplication(
        classLoader: ClassLoader?, name: String?, context: Context?
    ): Application {
        return super.newApplication(classLoader, PicPayAppTest::class.java.name, context)
    }
}