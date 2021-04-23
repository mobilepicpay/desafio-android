package com.picpay.desafio.android.rest_picpay.util

fun Any.loadResource(path: String) =
    this.javaClass
        .classLoader
        .getResourceAsStream(path)
        .bufferedReader()
        .use { it.readText() }