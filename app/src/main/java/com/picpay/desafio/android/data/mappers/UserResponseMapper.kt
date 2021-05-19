package com.picpay.desafio.android.data.mappers

import com.picpay.desafio.android.data.model.UserResponse
import com.picpay.desafio.android.domain.User

class UserResponseMapper {

    fun convertUserResponseToUser(userResponse: UserResponse): User = User(
        id = userResponse.id,
        name = userResponse.name,
        img = userResponse.img,
        username = userResponse.username
    )

}
