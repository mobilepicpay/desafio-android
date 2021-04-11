package com.picpay.desafio.android.shared.coroutine

import kotlinx.coroutines.CoroutineDispatcher

@Suppress("PropertyName")
interface CoroutineService {
    val Main: CoroutineDispatcher
    val IO: CoroutineDispatcher

    fun incrementIdlingResources()
    fun decrementIdlingResources()
}