package com.picpay.desafio.android.domain.repository

import com.picpay.desafio.android.data.model.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUser(): Flow<List<UserEntity>>
    fun getUpDateUsers(): Flow<List<UserEntity>>
}
