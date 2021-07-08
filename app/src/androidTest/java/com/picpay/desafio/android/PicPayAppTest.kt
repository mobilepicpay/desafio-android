package com.picpay.desafio.android

import android.app.Application


class PicPayAppTest: Application() {

   override fun onCreate() {
        super.onCreate()
        KoinInitTest.initialize(this)
    }
}