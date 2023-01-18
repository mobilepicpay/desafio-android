package com.picpay.desafio.android.core

sealed class Outcome<T : Any> {

    data class Success<T : Any>(val data: T) : Outcome<T>()
    data class Error<T : Any>(val error: DataError) : Outcome<T>()
    data class Loading<T : Any>(val data: T? = null) : Outcome<T>()
}
