package com.picpay.desafio.android.persistance.mappers

import com.picpay.desafio.android.persistance.models.UserEntity
import com.picpay.desafio.android.domain.models.User

 fun User.toEntity(): UserEntity {
    return UserEntity(
        name = name,
        id = id,
        img = img,
        username = username
    )
}

 fun List<User>.toEntity() : List<UserEntity> =
    this.map {
        UserEntity(
            img = it.img,
            id = it.id,
            username = it.username,
            name = it.name
        )
    }