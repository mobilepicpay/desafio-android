package com.picpay.desafio.android.data.local.mapper

import com.picpay.desafio.android.data.local.entities.UserDb
import com.picpay.desafio.android.data.model.User

class UserRemoteMapper {
    fun convertDbUserToUser(userDb: UserDb): User = User(
        id = userDb.id,
        name = userDb.name,
        img = userDb.img,
        username = userDb.username
    )
}