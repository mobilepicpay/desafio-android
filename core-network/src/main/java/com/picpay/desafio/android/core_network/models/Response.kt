package com.picpay.desafio.android.core_network.models

sealed class Response<T> {

    data class Success<T>(val data: T) : Response<T>()
    data class Error<T>(val networkError: NetworkError) : Response<T>()
}