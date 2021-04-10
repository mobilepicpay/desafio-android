package com.picpay.desafio.android.shared.domain

sealed class EntityError {
    object NoInternet: EntityError()
    object ServerError: EntityError()
}
