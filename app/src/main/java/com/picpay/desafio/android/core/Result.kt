package com.picpay.desafio.android.core

sealed class Result<out T, out E> {

    data class Success<T>(val value: T) : Result<T, Nothing>()

    data class Error<E>(val value: E) : Result<Nothing, E>()

    inline fun onSuccess(block: (T) -> Unit): Result<T, E> {
        if (this is Success) block(value)
        return this
    }

    inline fun onError(block: (E) -> Unit): Result<T, E> {
        if (this is Error) block(value)
        return this
    }
}