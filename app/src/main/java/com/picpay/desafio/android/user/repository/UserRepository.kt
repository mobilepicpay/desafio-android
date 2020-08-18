package com.picpay.desafio.android.user.repository

import com.picpay.desafio.android.user.model.User

interface UserRepository {
    suspend fun getUsers(): Result<List<User>>
    suspend fun getUsersCache(): Result<List<User>>
}