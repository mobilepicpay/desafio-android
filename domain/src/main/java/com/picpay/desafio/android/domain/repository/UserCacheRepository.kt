package com.picpay.desafio.android.domain.repository

import com.picpay.desafio.android.domain.models.User

interface UserCacheRepository {

    suspend fun save(users: List<User>)

    suspend fun cached(): List<User>
}