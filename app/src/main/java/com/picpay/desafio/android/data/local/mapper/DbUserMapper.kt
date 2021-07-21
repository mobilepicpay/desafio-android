package com.picpay.desafio.android.data.local.mapper

import com.picpay.desafio.android.data.local.entities.UserDb
import com.picpay.desafio.android.data.model.User

class DbUserMapper {
    fun convertUser(user: User): UserDb =
        UserDb(
            id = user.id,
            name = user.name,
            img = user.img,
            username = user.username
        )
}