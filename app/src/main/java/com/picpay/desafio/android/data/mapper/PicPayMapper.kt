package com.picpay.desafio.android.data.mapper

import com.picpay.desafio.android.data.model.User
import com.picpay.desafio.android.data.model.UserEntity
import com.picpay.desafio.android.data.model.UserResponse

interface PicPayMapper {

    suspend fun userResponseToUser(users: List<UserResponse>): List<User>

    suspend fun userEntityToUser(users: List<UserEntity>): List<User>

    suspend fun userToUserEntity(users: List<User>): List<UserEntity>
}
