package com.picpay.desafio.android.shared.domain

sealed class EntityResult<T, K> {
    data class Success<T, K>(val value: T) : EntityResult<T, K>()
    data class Error<T, K>(val value: K) : EntityResult<T, K>()
}