package com.picpay.desafio.android._config

import android.app.Application

fun Application.readFile(file: String): String {
    var content = ""
    var line: String?
    resources.assets.open(file).bufferedReader().use { buffer ->
        while (buffer.readLine().also { line = it } != null) {
            content += line
        }
    }
    return content
}