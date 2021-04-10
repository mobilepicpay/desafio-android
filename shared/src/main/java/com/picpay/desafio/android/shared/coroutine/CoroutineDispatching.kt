package com.picpay.desafio.android.shared.coroutine

import kotlinx.coroutines.CoroutineDispatcher

@Suppress("PropertyName")
interface CoroutineDispatching {
    val Main: CoroutineDispatcher
    val IO: CoroutineDispatcher
}