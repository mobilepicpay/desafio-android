package com.picpay.desafio.android.persistance.mappers

import com.picpay.desafio.android.persistance.models.UserEntity
import com.picpay.desafio.android.domain.models.User


 fun UserEntity.toDomain(): User =
    User(
        img = img,
        name =name,
        id = id,
        username = username
    )

 fun List<UserEntity>.toDomain() : List<User> =
    this.map {
        User(
            id = it.id,
            name = it.name,
            username = it.username,
            img = it.img
        )
    }