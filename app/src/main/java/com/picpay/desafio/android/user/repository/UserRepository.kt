package com.picpay.desafio.android.user.repository

import com.picpay.desafio.android.user.model.User
import com.picpay.desafio.android.user.repository.local.UserEntity
import com.picpay.desafio.android.user.repository.local.UserLocalDataSource
import com.picpay.desafio.android.user.repository.remote.UserRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val userLocalDataSource: UserLocalDataSource
) {
    suspend fun getContactsRemote(): List<User> = withContext(Dispatchers.IO) {
        val response = userRemoteDataSource.getUsers()
        saveListInLocal(response)
        response
    }

    suspend fun getContactsLocal(): List<User> {
        return userLocalDataSource.getAllUsers().map {
            User(
                it.id,
                it.img,
                it.name,
                it.username
            )
        }
    }

    private suspend fun saveListInLocal(users: List<User>) {
        userLocalDataSource.removeLocalAndInsertAllUsers(
            users.map {
                UserEntity(
                    it.id,
                    it.img,
                    it.name,
                    it.username
                )
            }
        )
    }
}
