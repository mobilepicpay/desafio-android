package com.picpay.desafio.android.data_remote.repository

import com.picpay.desafio.android.networking.HttpErrorConvert
import com.picpay.desafio.android.networking.NetworkingErrorConvert

private val transformers = listOf(
    HttpErrorConvert,
    NetworkingErrorConvert
)

suspend fun <T> managedExecution(target: suspend () -> T): T =
    try {
        target.invoke() 
    } catch (incoming: Throwable) {
        throw transformers.map { errorTransformer ->
            errorTransformer.convert(incoming)
        }.reduce { transformed, another ->
                when {
                    transformed == another -> transformed
                    another == incoming -> transformed
                    else -> another
                }
            }
    }