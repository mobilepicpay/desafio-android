package com.picpay.desafio.android.responses

sealed class ResultRemote<out T> {
    data class Success<out T>(val response: T): ResultRemote<T>()

    sealed class ErrorResponse(open val throwable: Throwable) : ResultRemote<Nothing>(){
        data class Unknown(override val throwable: Throwable): ErrorResponse(throwable)
        data class TokenExpired(override val throwable: Throwable): ErrorResponse(throwable)
    }
}