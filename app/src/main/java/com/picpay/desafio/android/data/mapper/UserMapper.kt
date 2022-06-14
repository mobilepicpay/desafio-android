package com.picpay.desafio.android.data.mapper

import com.picpay.desafio.android.data.entities.UserEntity
import com.picpay.desafio.android.domain.model.UserModel

fun UserEntity.toModel() = UserModel(img = img, name = name, username = username)

fun List<UserEntity>.toListModel() = map { it.toModel() }