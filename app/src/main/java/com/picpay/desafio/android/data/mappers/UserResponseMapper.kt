package com.picpay.desafio.android.data.mappers

import com.picpay.desafio.android.data.model.UserResponse
import com.picpay.desafio.android.domain.User

class UserResponseMapper {

    fun convertUserResponseToUser(userResponse: UserResponse): User = User(
        id = userResponse.id,
        name = userResponse.name,
        imgUrl = userResponse.imgUrl,
        userName = userResponse.userName
    )

}
