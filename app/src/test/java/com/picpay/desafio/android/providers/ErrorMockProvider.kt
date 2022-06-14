package com.picpay.desafio.android.providers

import kotlinx.coroutines.flow.flow

object ErrorMockProvider {
    fun mockErrorFlow() = flow { emit(throw Exception()) }
}