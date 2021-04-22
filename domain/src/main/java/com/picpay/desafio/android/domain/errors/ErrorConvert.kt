package com.picpay.desafio.android.domain.errors

interface ErrorConvert {

    suspend fun convert(incoming: Throwable): Throwable
}