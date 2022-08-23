package com.picpay.desafio.android.users.data.mapper

import com.picpay.desafio.android.users.data.api.response.UserResponse
import com.picpay.desafio.android.users.domain.model.User
import javax.inject.Inject

class UserResponseToModelMapper @Inject constructor() {

    fun mapFrom(response: UserResponse) = User(
        id = response.id,
        img = response.img,
        name = response.name,
        username = response.username
    )
}