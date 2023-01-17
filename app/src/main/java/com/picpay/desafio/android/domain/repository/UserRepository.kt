package com.picpay.desafio.android.domain.repository

import com.picpay.desafio.android.core.DataResult
import com.picpay.desafio.android.data.model.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getUser(): Flow<DataResult<List<UserEntity>>?>
}
