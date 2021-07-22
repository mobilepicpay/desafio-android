package com.picpay.desafio.android.data.remote.mapper

import com.picpay.desafio.android.data.model.User
import com.picpay.desafio.android.data.remote.model.UserResponse

fun List<UserResponse>.toUserModel() = map {
    User(
        img = it.img,
        name = it.name,
        id = it.id,
        username = it.username
    )
}