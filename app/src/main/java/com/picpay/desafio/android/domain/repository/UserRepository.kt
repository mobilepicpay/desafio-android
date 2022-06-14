package com.picpay.desafio.android.domain.repository

import com.picpay.desafio.android.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUsers(): Flow<List<UserModel>>
}