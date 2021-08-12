package com.picpay.desafio.android.data.api.mappers

interface RemoteMapper<T, R> {

    fun fromResponseToModel(response: T): R
}
