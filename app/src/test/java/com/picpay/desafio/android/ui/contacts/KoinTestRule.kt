package com.picpay.desafio.android.ui.contacts

import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.module.Module

class KoinTestRule(
    private val module: Module
) : TestWatcher() {
    override fun starting(description: Description) {
        startKoin {
            modules(module)
        }
    }

    override fun finished(description: Description) {
        stopKoin()
    }
}