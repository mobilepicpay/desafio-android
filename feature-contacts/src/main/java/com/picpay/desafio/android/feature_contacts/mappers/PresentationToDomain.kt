package com.picpay.desafio.android.feature_contacts.mappers

import com.picpay.desafio.android.domain.models.User
import com.picpay.desafio.android.feature_contacts.models.UserPresentation

fun UserPresentation.toDomain(): User {
    return User(
        img = img,
        name = name,
        id = id,
        username = username
    )
}