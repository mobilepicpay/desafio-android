package com.picpay.desafio.android.uitest

import com.picpay.desafio.android.AppConfig

class TestAppConfig: AppConfig() {
    override fun getBaseUrl() = "http://127.0.0.1:8080/"
}