package com.picpay.desafio.android.data.api.mappers

import com.picpay.desafio.android.data.api.responses.UserResponse
import com.picpay.desafio.android.models.User
import javax.inject.Inject

class UserRemoteMapper @Inject constructor() : RemoteMapper<UserResponse, User> {

    override fun fromResponseToModel(response: UserResponse): User {
        return response.run {
            User(id, name, image, username)
        }
    }
}
