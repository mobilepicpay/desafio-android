package com.picpay.desafio.android.mapper

import com.picpay.desafio.android.data.User
import com.picpay.desafio.android.data.UserEntity
import com.picpay.desafio.android.data.UserResponse

object UserMapper {

    fun toUser(userResponse: UserResponse) = User(
        name = userResponse.name,
        username = userResponse.username,
        img = userResponse.img
    )

    fun toUser(userEntity: UserEntity) = User(
        name = userEntity.name,
        username = userEntity.username,
        img = userEntity.img
    )

    fun toUserEntity(userResponse: UserResponse) = UserEntity(
        id = userResponse.id,
        name = userResponse.name,
        username = userResponse.username,
        img = userResponse.img
    )
}
