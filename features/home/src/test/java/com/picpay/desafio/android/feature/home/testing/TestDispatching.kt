package com.picpay.desafio.android.feature.home.testing

import com.picpay.desafio.android.shared.coroutine.CoroutineDispatching
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher

@Suppress("EXPERIMENTAL_API_USAGE")
class TestDispatching : CoroutineDispatching {

    private val dispatcher = TestCoroutineDispatcher()

    override val Main: CoroutineDispatcher
        get() = dispatcher
    override val IO: CoroutineDispatcher
        get() = dispatcher

    fun clean() {
        dispatcher.cleanupTestCoroutines()
    }
}