package com.picpay.desafio.android.data.utils

sealed class Operation<out DataType> {
    data class Success<out DataType>(val dataType: DataType) : Operation<DataType>()
    data class Error(val throwable: Throwable) : Operation<Nothing>()
}