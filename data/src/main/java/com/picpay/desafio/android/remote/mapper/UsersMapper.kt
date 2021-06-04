package com.picpay.desafio.android.remote.mapper

import com.picpay.desafio.android.entities.UsersDomain
import com.picpay.desafio.android.remote.model.UserData
import com.picpay.desafio.android.remote.model.UserPayload

object UsersMapper {

    fun map(payload: UserPayload) = payload.users.map { map(it) }

    private fun map(payload: UserData) = UsersDomain(
        img = payload.img,
        name = payload.name,
        id = payload.id,
        username = payload.username
    )
}