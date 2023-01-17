package com.picpay.desafio.android.domain.repository

import com.picpay.desafio.android.core.Outcome
import com.picpay.desafio.android.data.model.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUser(): Flow<Outcome<List<UserEntity>>?>
}
