package com.picpay.desafio.android.data.local.mapper

import com.picpay.desafio.android.data.local.entities.UserDb
import com.picpay.desafio.android.data.model.User

fun List<UserDb>.convertToUser() = map {
    User(
        id = it.id,
        name = it.name,
        img = it.img,
        username = it.username
    )
}

fun List<User>.convertToUserDb() = map {
    UserDb(
        id = it.id,
        name = it.name,
        img = it.img,
        username = it.username
    )
}