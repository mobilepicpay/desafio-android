package com.picpay.desafio.android.repository

import com.picpay.desafio.android.data.User
import com.picpay.desafio.android.data.UserEntity
import com.picpay.desafio.android.data.UserResponse

interface PicPayRepository {

    suspend fun insertUsersToLocal(users: List<UserEntity>): List<Long>

    suspend fun getUsersFromLocal(): List<UserEntity>

    suspend fun getUsersFromRemote(): List<UserResponse>

    fun mapperUserEntityToUser(entityList: List<UserEntity>): List<User>

    fun mapperUserResponseToUser(responseList: List<UserResponse>): List<User>

    fun mapperUserResponseToUserEntity(responseList: List<UserResponse>): List<UserEntity>
}
