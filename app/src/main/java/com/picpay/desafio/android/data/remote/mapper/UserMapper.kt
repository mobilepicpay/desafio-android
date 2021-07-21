package com.picpay.desafio.android.data.remote.mapper

import com.picpay.desafio.android.data.model.User
import com.picpay.desafio.android.data.remote.model.UserResponse

class UserMapper {
    fun userResponse(userResponse: UserResponse): User {
        return User(
            img = userResponse.img,
            name = userResponse.name,
            id = userResponse.id,
            username = userResponse.username
        )
    }
}