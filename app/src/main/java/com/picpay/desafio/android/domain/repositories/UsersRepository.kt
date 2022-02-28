package com.picpay.desafio.android.domain.repositories

import com.picpay.desafio.android.domain.common.Result
import com.picpay.desafio.android.domain.entities.UserEntity

interface UsersRepository {
    suspend fun getRemoteUsers(): Result<List<UserEntity>>
    suspend fun getCachedUsers(): Result<List<UserEntity>>
}