package com.picpay.desafio.android.data.mapper

import com.picpay.desafio.android.data.model.UserResponse
import com.picpay.desafio.android.domain.model.User

fun UserResponse.toDomain(): User {
    return User(
        img = img,
        name = name,
        id = id,
        username = username
    )
}

fun List<UserResponse>.toDomain(): List<User> = map { it.toDomain() }
