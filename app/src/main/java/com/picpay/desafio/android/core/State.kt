package com.picpay.desafio.android.core

sealed class State<out T : Any> {
    object Loading : State<Nothing>()
    data class Success<out T : Any>(val result: T) : State<T>()
    data class Error(val error: Throwable) : State<Nothing>()
}