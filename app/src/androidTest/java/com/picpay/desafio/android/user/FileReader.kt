package com.picpay.desafio.android.user

import androidx.test.platform.app.InstrumentationRegistry

object FileReader {
    fun readStringFromFile(fileName: String): String {

        val assets = InstrumentationRegistry.getInstrumentation().context.assets
        return assets.open(fileName).reader().readText()
    }
}