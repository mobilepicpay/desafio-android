package com.picpay.desafio.android.core

import kotlinx.coroutines.flow.Flow

interface FlowUseCase<T : Any> {
    fun invoke(): Flow<Outcome<T>>
}
