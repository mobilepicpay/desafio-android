package com.picpay.desafio.android.providers

import kotlinx.coroutines.flow.flow

object MockErrorProvider {
    fun mockErrorFlow() = flow { emit(throw Exception()) }
}