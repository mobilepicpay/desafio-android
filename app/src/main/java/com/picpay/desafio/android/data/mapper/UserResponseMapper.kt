package com.picpay.desafio.android.data.mapper

import com.picpay.desafio.android.data.dto.UserResponse
import com.picpay.desafio.android.domain.model.User

object UserResponseMapper {

    private fun toUserModel(response: UserResponse) =
        User(
            name = response.name,
            id = response.id,
            img = response.img,
            username = response.username
        )

    fun toUsersModel(response: List<UserResponse>): List<User> =
        response.map { toUserModel(it) }

}