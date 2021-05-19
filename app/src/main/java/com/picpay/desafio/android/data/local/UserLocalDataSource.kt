package com.picpay.desafio.android.data.local

import com.picpay.desafio.android.domain.User

interface UserLocalDataSource {

    suspend fun getAllUsers(): List<User>

    suspend fun updateCacheDB(users: List<User>)

}