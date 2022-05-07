package com.picpay.desafio.android.domain.models

import com.picpay.desafio.android.presentation.model.UserPresentable

internal data class User(
    val img: String,
    val name: String,
    val id: Int,
    val username: String
)

internal fun User.mapToUserPresentable() = UserPresentable(
    img = img,
    name = if (name.isEmpty()) name else "-",
    id = id,
    username = if (username.isEmpty()) username else "-"
)
