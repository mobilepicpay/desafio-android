package com.picpay.desafio.android.data.mapper

import com.picpay.desafio.android.data.entities.UserEntity
import com.picpay.desafio.android.domain.model.ContactModel

fun UserEntity.toContactModel() = ContactModel(image = img, name = name, username = username)

fun List<UserEntity>.toListContactModel() = map { it.toContactModel() }