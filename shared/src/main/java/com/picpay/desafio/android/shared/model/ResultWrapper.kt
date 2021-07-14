package com.picpay.desafio.android.shared.model

/**
 * @author Vitor Herrmann on 12/07/2021
 */
sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    data class Error(val throwable: Throwable) : ResultWrapper<Nothing>()
}
