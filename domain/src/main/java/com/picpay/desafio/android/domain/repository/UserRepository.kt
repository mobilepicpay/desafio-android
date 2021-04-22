package com.picpay.desafio.android.domain.repository

import com.picpay.desafio.android.domain.models.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun getUsers(): List<User>

}