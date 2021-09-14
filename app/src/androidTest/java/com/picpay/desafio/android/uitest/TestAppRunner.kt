package com.picpay.desafio.android.uitest

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class TestAppRunner: AndroidJUnitRunner() {
    override fun newApplication(cl: ClassLoader?, className: String?,
                                context: Context?): Application {
        return super.newApplication(cl, TestApp::class.java.name, context)
    }
}