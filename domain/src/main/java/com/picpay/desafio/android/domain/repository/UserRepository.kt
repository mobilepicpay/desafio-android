package com.picpay.desafio.android.domain.repository

import com.picpay.desafio.android.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    val users: Flow<List<User>>
    suspend fun refreshUsers()
}
