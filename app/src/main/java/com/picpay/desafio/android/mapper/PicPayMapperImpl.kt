package com.picpay.desafio.android.mapper

import com.picpay.desafio.android.data.User
import com.picpay.desafio.android.data.UserEntity
import com.picpay.desafio.android.data.UserResponse

class PicPayMapperImpl : PicPayMapper {

    override suspend fun userResponseToUser(users: List<UserResponse>): List<User> {
        return users.map { userResponse ->
            User(
                id = userResponse.id,
                name = userResponse.name,
                username = userResponse.username,
                img = userResponse.img
            )
        }
    }

    override suspend fun userEntityToUser(users: List<UserEntity>): List<User> {
        return users.map { userEntity ->
            User(
                id = userEntity.id,
                name = userEntity.name,
                username = userEntity.username,
                img = userEntity.img
            )
        }
    }

    override suspend fun userToUserEntity(users: List<User>): List<UserEntity> {
        return users.map { user ->
            UserEntity(
                id = user.id,
                name = user.name,
                username = user.username,
                img = user.img
            )
        }
    }
}
