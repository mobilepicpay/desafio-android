package com.picpay.desafio.android.network

sealed class ResultWrapper<out T> {
    data class Success<out T>(val content: T) : ResultWrapper<T>()
    data class Error(val throwable: Throwable) : ResultWrapper<Nothing>()
}