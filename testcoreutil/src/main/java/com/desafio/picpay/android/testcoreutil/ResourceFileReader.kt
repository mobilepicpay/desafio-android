package com.desafio.picpay.android.testcoreutil

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object ResourceFileReader {

    fun read(file: String): String {
        return javaClass.classLoader
            ?.getResourceAsStream(file)
            ?.reader()
            ?.readText()!!
    }

    inline fun <reified T> parseJson(file: String): List<T> {
        return Gson().fromJson<List<T>>(read(file), object : TypeToken<List<T>>() {}.type)
    }
}