package com.picpay.desafio.android.data.source.local

import javax.inject.Inject

class LocalDataSource @Inject constructor(private val dao: UserDao) {

    fun getAllUsers() = dao.getAllUsers()

    suspend fun insertUsers(list: List<UserDb>) {
        dao.insertUsers(list)
    }

    suspend fun deleteUsers() {
        dao.deleteAllUsers()
    }

}