package com.picpay.desafio.android.feature_contacts.mappers

import com.picpay.desafio.android.domain.models.User
import com.picpay.desafio.android.feature_contacts.models.UserPresentation

fun User.toPresentation(): UserPresentation {
    return UserPresentation(
        img = img,
        name = name,
        id = id,
        username = username
    )
}

fun List<User>.toListPresentation(): List<UserPresentation> =
    this.map {
        UserPresentation(
            img = it.img,
            name = it.name,
            id = it.id,
            username = it.username
        )
    }
