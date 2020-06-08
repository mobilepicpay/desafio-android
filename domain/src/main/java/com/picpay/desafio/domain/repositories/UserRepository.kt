package com.picpay.desafio.domain.repositories

import com.picpay.desafio.domain.result.Result
import com.picpay.desafio.domain.models.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun getUser(userId: Int): Flow<Result<User>>

    suspend fun getUsers(): Flow<Result<List<User>>>

}