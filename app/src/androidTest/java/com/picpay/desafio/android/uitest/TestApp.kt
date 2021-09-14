package com.picpay.desafio.android.uitest

import com.picpay.desafio.android.App
import com.picpay.desafio.android.AppConfig

class TestApp: App() {
    override fun getConfig(): AppConfig = TestAppConfig()
}