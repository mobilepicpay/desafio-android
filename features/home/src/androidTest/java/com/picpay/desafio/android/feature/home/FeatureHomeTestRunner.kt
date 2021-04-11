package com.picpay.desafio.android.feature.home

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class FeatureHomeTestRunner : AndroidJUnitRunner() {

    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        return super.newApplication(cl, FeatureHomeApplication::class.java.name, context)
    }
}