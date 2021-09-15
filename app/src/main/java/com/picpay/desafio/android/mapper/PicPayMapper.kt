package com.picpay.desafio.android.mapper

import com.picpay.desafio.android.data.User
import com.picpay.desafio.android.data.UserEntity
import com.picpay.desafio.android.data.UserResponse

interface PicPayMapper {

    suspend fun userResponseToUser(users: List<UserResponse>): List<User>

    suspend fun userEntityToUser(users: List<UserEntity>): List<User>

    suspend fun userToUserEntity(users: List<User>): List<UserEntity>
}
