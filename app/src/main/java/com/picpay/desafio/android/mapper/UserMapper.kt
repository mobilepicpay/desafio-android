package com.picpay.desafio.android.mapper

import com.picpay.desafio.android.data.User
import com.picpay.desafio.android.data.UserResponse

object UserMapper {

    fun toUser(userResponse: UserResponse) = User(
        name = userResponse.name,
        username = userResponse.username,
        img = userResponse.img
    )
}
