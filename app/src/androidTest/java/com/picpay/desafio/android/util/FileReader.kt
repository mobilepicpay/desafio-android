package com.picpay.desafio.android.util

import androidx.test.platform.app.InstrumentationRegistry
import dagger.hilt.android.testing.HiltTestApplication
import java.io.IOException
import java.io.InputStreamReader

object FileReader {

    fun readStringFromFile(fileName: String): String {
        try {
            val inputStream =
                (InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as HiltTestApplication).assets.open(
                    fileName
                )
            val builder = StringBuilder()
            val reader = InputStreamReader(inputStream, "UTF-8")
            reader.readLines().forEach {
                builder.append(it)
            }
            return builder.toString()
        } catch (e: IOException) {
            throw e
        }
    }
}