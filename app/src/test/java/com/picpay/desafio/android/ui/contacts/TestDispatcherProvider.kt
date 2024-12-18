package com.picpay.desafio.android.ui.contacts

import com.picpay.desafio.android.data.coroutine.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher

@OptIn(ExperimentalCoroutinesApi::class)
class TestDispatcherProvider(
    testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
) : DispatcherProvider {
    override val Default: CoroutineDispatcher = testDispatcher
    override val IO: CoroutineDispatcher = testDispatcher
    override val Main: CoroutineDispatcher = testDispatcher
}