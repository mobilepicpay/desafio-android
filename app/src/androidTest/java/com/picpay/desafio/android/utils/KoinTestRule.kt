package com.picpay.desafio.android.utils

import androidx.test.core.app.ApplicationProvider
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class KoinTestRule : TestWatcher() {

    override fun starting(description: Description?) {
        super.starting(description)
        stopKoin()
        startKoin {
            androidContext(ApplicationProvider.getApplicationContext())
        }
    }

    override fun finished(description: Description?) {
        super.finished(description)
        stopKoin()
    }
}