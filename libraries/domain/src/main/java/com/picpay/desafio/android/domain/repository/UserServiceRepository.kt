package com.picpay.desafio.android.domain.repository

import com.picpay.desafio.android.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserServiceRepository {

    suspend fun getUsers(): Flow<List<User>>

}
