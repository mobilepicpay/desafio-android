package com.picpay.desafio.android.data.local.mapper

import com.picpay.desafio.android.data.local.model.UserLocal
import com.picpay.desafio.android.domain.entities.User

object UserLocalMapper {
    fun fromUserLocalList(usersResponse: List<UserLocal>): List<User> {
        return usersResponse.map {
            User(
                img = it.img ?: "",
                name = it.name ?: "",
                id = it.id ?: 0,
                username = it.username ?: ""
            )
        }
    }

    fun toUserLocalList(usersResponse: List<User>): List<UserLocal> {
        return usersResponse.map {
            UserLocal(
                img = it.img,
                name = it.name,
                id = it.id,
                username = it.username
            )
        }
    }
}