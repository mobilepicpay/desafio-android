package com.picpay.desafio.android.data.remote.mapper

import com.picpay.desafio.android.data.remote.response.UserRemoteResponse
import com.picpay.desafio.android.domain.entities.User

object UserRemoteMapper {
    fun fromListUserRemoteResponse(usersResponse: List<UserRemoteResponse>): List<User> {
        return usersResponse.map {
            User(
                img = it.img ?: "",
                name = it.name ?: "",
                id = it.id ?: 0,
                username = it.username ?: ""
            )
        }
    }
}