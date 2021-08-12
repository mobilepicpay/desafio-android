package com.picpay.desafio.android.data.db.mappers

interface LocalMapper<T, R> {

    fun fromEntityToModel(entity: T): R
    fun fromModelToEntity(model: R): T
}
