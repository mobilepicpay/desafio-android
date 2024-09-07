package com.picpay.desafio.android.core.data.repository

import com.picpay.desafio.android.core.model.ApiResponse
import com.picpay.desafio.android.core.model.UserModel
import com.picpay.desafio.android.core.network.model.UserResponse
import kotlinx.coroutines.flow.Flow

interface UserRemoteDataSource {
    suspend fun getUsers(): ApiResponse<List<UserResponse>>
}

interface UserLocalDataSource {

    fun searchUser(
        searchQuery: String,
        sortColumn: String,
        sortOrder: String
    ): Flow<List<UserModel>>

    fun getUsers(): Flow<List<UserModel>>

    suspend fun insertUser(user: UserModel)

    suspend fun insertUsers(vararg user: UserModel): Boolean
}