package com.picpay.desafio.android.domain.repository

import com.picpay.desafio.android.domain.model.User

interface UserRepository {
    suspend fun getUsers(): List<User>
}
