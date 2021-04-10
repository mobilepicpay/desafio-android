package com.picpay.desafio.android.shared.domain

sealed class EntityResult<T> {
    data class Success<T>(val data: T) : EntityResult<T>()
    data class Error<T>(val type: EntityError) : EntityResult<T>()
}