package com.picpay.desafio.android.data.remote.util

import com.picpay.desafio.data.BuildConfig

object BaseURL {
    var isTesting: Boolean = false
    fun getURL(): String {
        return if (!isTesting) BuildConfig.BASE_URL else LOCAL_BASE_URL
    }

    private const val LOCAL_BASE_URL = "http://127.0.0.1:8080/"
}