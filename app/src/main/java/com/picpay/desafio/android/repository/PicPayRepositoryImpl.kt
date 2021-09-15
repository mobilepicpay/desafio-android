package com.picpay.desafio.android.repository

import com.picpay.desafio.android.data.User
import com.picpay.desafio.android.data.UserEntity
import com.picpay.desafio.android.data.UserResponse
import com.picpay.desafio.android.mapper.PicPayMapper
import com.picpay.desafio.android.repository.local.PicPayDatabase
import com.picpay.desafio.android.repository.remote.PicPayService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PicPayRepositoryImpl(
    private val api: PicPayService,
    private val database: PicPayDatabase,
    private val mapper: PicPayMapper
) : PicPayRepository {

    override suspend fun insertUsersToLocal(users: List<UserEntity>): List<Long> {
        return withContext(Dispatchers.IO) { database.userDao().insert(*users.toTypedArray()) }
    }

    override suspend fun getUsersFromLocal(): List<UserEntity> {
        return withContext(Dispatchers.IO) { database.userDao().getUsers() }
    }

    override suspend fun getUsersFromRemote(): List<UserResponse> {
        return withContext(Dispatchers.IO) { api.getUsers() }
    }

    override suspend fun mapperUserEntityToUser(entityList: List<UserEntity>): List<User> {
        return withContext(Dispatchers.Default) { mapper.userEntityToUser(entityList) }
    }

    override suspend fun mapperUserResponseToUser(responseList: List<UserResponse>): List<User> {
        return withContext(Dispatchers.Default) { mapper.userResponseToUser(responseList) }
    }

    override suspend fun mapperUserResponseToUserEntity(responseList: List<UserResponse>): List<UserEntity> {
        return withContext(Dispatchers.Default) { mapper.userResponseToUserEntity(responseList) }
    }
}
