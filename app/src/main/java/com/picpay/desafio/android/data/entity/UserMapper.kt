package com.picpay.desafio.android.data.entity

import com.picpay.desafio.android.domain.model.UserModel

fun UserDTO.toModel() =
    UserModel(
        id = id,
        img = img,
        name = name,
        username = username
    )

fun List<UserDTO>.toModel(): List<UserModel> =
    this.map {
        it.toModel()
    }