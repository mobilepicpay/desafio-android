package com.picpay.desafio.android.repository

import com.picpay.desafio.android.data.UserEntity
import com.picpay.desafio.android.data.UserResponse
import com.picpay.desafio.android.repository.local.PicPayDatabase
import com.picpay.desafio.android.repository.remote.PicPayService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.await

class PicPayRepositoryImpl(
    private val api: PicPayService,
    private val database: PicPayDatabase
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
}
