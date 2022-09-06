package com.picpay.desafio.android.provider

import kotlinx.coroutines.flow.flow

object MockErrorProvider {
    fun mockErrorFlow() = flow {
        emit(throw Exception("Mock error provider"))
    }
}