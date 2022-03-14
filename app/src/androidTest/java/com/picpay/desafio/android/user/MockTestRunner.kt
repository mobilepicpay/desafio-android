package com.picpay.desafio.android.user

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class MockTestRunner : AndroidJUnitRunner () {

    override fun  newApplication (cl: ClassLoader ?, className: String ?,
                                  context: Context ?) : Application {
        return  super .newApplication(cl, UserTestApp:: class .java.name, context)
    }
}