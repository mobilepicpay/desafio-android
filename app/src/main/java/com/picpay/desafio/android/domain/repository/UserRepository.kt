package com.picpay.desafio.android.domain.repository

import com.picpay.desafio.android.data.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUser(isGetCacheValues: Boolean): Flow<List<UserEntity>>
}
