package com.picpay.desafio.android._config

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import org.koin.test.KoinTest

abstract class BaseKoinTest : KoinTest {

    val application: Application = ApplicationProvider.getApplicationContext()
}