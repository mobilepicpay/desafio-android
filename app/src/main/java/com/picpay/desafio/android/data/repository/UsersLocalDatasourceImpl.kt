package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.local.UserDao
import com.picpay.desafio.android.data.models.UserDb

class UsersLocalDatasourceImpl(private val db: UserDao) : UsersLocalDatasource {

    override suspend fun getUsers() = db.getUsers()

    override suspend fun saveUsers(users: List<UserDb>) {
        users.forEach { db.addUser(it) }
    }
}