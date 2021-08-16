package com.picpay.desafio.android.helper

import io.mockk.mockkClass
import org.junit.After
import org.junit.Rule
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest

abstract class MockKoinTest(
    relaxed: Boolean = false,
    relaxUnitFun: Boolean = false
) : KoinTest {

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        mockkClass(type = clazz, relaxed = relaxed, relaxUnitFun = relaxUnitFun)
    }

    @After
    fun after() {
        stopKoin()
    }
}