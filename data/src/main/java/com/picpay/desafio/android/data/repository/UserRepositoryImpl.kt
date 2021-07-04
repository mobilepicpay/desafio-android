package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.local.datasource.UserLocalDataSource
import com.picpay.desafio.android.data.local.mapper.UserLocalMapper
import com.picpay.desafio.android.data.remote.datasource.UserRemoteDataSource
import com.picpay.desafio.android.domain.entities.User
import com.picpay.desafio.android.domain.repository.UserRepository
import com.picpay.desafio.android.domain.result.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepositoryImpl(
    private val remoteDataSource: UserRemoteDataSource,
    private val localDataSource: UserLocalDataSource
) : UserRepository {
    override suspend fun getUsers(isConnected: Boolean): ResultWrapper<List<User>> {
        return withContext(Dispatchers.IO) {
            if (isConnected) {
                val response = remoteDataSource.getUsers()
                if (response is ResultWrapper.Success) {
                    localDataSource.updateUsers(UserLocalMapper.toUserLocalList(response.value))
                    response
                } else {
                    localDataSource.getUsers()
                }
            } else {
                localDataSource.getUsers()
            }
        }
    }
}