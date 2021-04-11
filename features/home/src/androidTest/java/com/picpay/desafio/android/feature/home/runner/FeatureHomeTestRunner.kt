package com.picpay.desafio.android.feature.home.runner

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.picpay.desafio.android.feature.home.FeatureHomeApplication

class FeatureHomeTestRunner : AndroidJUnitRunner() {

    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        return super.newApplication(cl, FeatureHomeApplication::class.java.name, context)
    }
}