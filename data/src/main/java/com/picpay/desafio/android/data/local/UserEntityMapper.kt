package com.picpay.desafio.android.data.local

import com.picpay.desafio.android.data.api.UserDTO
import com.picpay.desafio.android.domain.model.User

fun UserDTO.toUser(): User? {
    if (id == null || name == null || img == null || username == null) return null
    return User(id, name, img, username)
}
