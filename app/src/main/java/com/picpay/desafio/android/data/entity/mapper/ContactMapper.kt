package com.picpay.desafio.android.data.entity

import com.picpay.desafio.android.domain.model.ContactModel

fun ContactDTO.toModel() =
    ContactModel(
        id = id,
        img = img,
        name = name,
        username = username
    )

fun List<ContactDTO>.toModel(): List<ContactModel> =
    this.map {
        it.toModel()
    }