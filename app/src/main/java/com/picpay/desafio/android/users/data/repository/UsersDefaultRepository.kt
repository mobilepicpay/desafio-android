package com.picpay.desafio.android.users.data.repository

import com.picpay.desafio.android.core.Result
import com.picpay.desafio.android.users.data.datasource.UsersRemoteDataSource
import com.picpay.desafio.android.users.domain.model.UserError
import com.picpay.desafio.android.users.domain.model.User
import javax.inject.Inject

class UsersDefaultRepository @Inject constructor(
    private val remoteDataSource: UsersRemoteDataSource
) : UsersRepository {

    override suspend fun getUsers(): Result<List<User>, UserError> {
        return remoteDataSource.getUsers()
    }

}
