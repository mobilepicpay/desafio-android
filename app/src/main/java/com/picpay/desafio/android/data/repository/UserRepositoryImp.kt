package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.local.UserLocalDataSource
import com.picpay.desafio.android.data.remote.UserRemoteDataSource
import com.picpay.desafio.android.domain.User
import java.io.IOException

class UserRepositoryImp(
    private val localDataSource: UserLocalDataSource,
    private val remoteDataSource: UserRemoteDataSource
) : UserRepository {


    override suspend fun getAllUser(): List<User> {

        return try {

            val remote = remoteDataSource.getAllUser()
            if (remote.isNotEmpty()) {
                localDataSource.updateCache(remote)
                remote
            } else {
                localDataSource.getAllUsers()
            }

        } catch (ioException: IOException) {
            localDataSource.getAllUsers()
        }.sortedBy { it.name }
    }
}

