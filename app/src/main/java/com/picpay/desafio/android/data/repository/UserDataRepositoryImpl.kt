package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.local.datasource.UserLocalDataSource
import com.picpay.desafio.android.data.model.User
import com.picpay.desafio.android.data.remote.datasource.UserRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserDataRepositoryImpl(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val localDataSource: UserLocalDataSource
) : UserDataRepository {

    override suspend fun getUserList(): List<User> {
        return withContext(Dispatchers.IO) {
            userRemoteDataSource.getUserData()
        }
    }

    override suspend fun getUserCache(): List<User> {
        return withContext(Dispatchers.IO) {
            localDataSource.getUserData()
        }
    }

    override suspend fun saveUserCache(userList: List<User>) {
        withContext(Dispatchers.IO) {
            localDataSource.setUserCache(userList)
        }
    }
}