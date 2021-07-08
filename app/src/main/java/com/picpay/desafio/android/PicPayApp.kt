package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.initializers.KoinInitializer

open class PicPayApp : Application() {

    override fun onCreate() {
        super.onCreate()
        KoinInitializer.initialize(this)
    }
}