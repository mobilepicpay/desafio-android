package com.picpay.desafio.android.core

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest

abstract class FlowUseCase<T> {

    private val _trigger = MutableStateFlow(true)

    val resultFlow: Flow<T> = _trigger.flatMapLatest {
        performAction()
    }

    suspend fun launch() {
        _trigger.emit(!(_trigger.value))
    }

    protected abstract fun performAction(): Flow<T>
}
