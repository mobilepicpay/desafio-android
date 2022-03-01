package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.local.UserDao
import com.picpay.desafio.android.data.models.UserResponse

class UsersLocalDatasourceImpl(private val db: UserDao) : UsersLocalDatasource {

    override suspend fun getUsers() = db.getUsers()

    override suspend fun saveUsers(users: List<UserResponse>) {
        if (users.isNotEmpty())
            db.clearAllUsers()

        users.forEach { db.addUser(it) }
    }
}