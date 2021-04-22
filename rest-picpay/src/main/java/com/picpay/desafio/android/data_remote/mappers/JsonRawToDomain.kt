package com.picpay.desafio.android.data_remote.mappers

import com.picpay.desafio.android.data_remote.models.RawUserResponse
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