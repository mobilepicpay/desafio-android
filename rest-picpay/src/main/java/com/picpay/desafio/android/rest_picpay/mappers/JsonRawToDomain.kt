package com.picpay.desafio.android.rest_picpay.mappers

import com.picpay.desafio.android.rest_picpay.models.RawUserResponse
import com.picpay.desafio.android.domain.models.User

internal fun List<RawUserResponse>.toDomain() =
    this.map {
        User(
            username = it.username,
            id = it.id,
            name = it.name,
            img = it.img
        )
    }