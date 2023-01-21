package com.picpay.desafio.android.core

import kotlinx.coroutines.flow.Flow

interface FlowUseCase<T : Params, V : Any> {
    fun invoke(params: T): Flow<Outcome<V>>
}

interface Params

object None : Params
