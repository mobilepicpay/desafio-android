package com.picpay.desafio.android.data.db.datasources

import com.picpay.desafio.android.models.User

interface UserLocalDataSource {

    suspend fun insertUsers(users: List<User>)
    suspend fun getUsers(): List<User>
}
