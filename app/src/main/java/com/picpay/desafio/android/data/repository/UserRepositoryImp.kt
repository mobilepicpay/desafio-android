package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.local.UserLocalDataSource
import com.picpay.desafio.android.data.remote.UserRemoteDataSource
import com.picpay.desafio.android.domain.User

class UserRepositoryImp(
    private val localDataSource: UserLocalDataSource,
    private val remoteDataSource: UserRemoteDataSource
) : UserRepository {


    override suspend fun getAllUser(): List<User> {
        val remote = remoteDataSource.getAllUser()

        val result = if(remote.isNotEmpty()){
            localDataSource.updateCacheDB(remote)
            remote
        } else {
            localDataSource.getAllUsers()
        }
        return result.sortedBy { it.name }
    }


}