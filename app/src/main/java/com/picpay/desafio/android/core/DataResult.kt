package com.picpay.desafio.android.core

sealed class DataResult<T : Any> {

    data class Success<T : Any>(val data: T) : DataResult<T>()
    data class Error<T : Any>(val error: RemoteDataSourceError) : DataResult<T>()
    data class Loading<T : Any>(val data: T? = null) : DataResult<T>()
}
