package com.picpay.desafio.android.users.data.datasource

import com.picpay.desafio.android.core.Result
import com.picpay.desafio.android.users.data.model.UserError
import com.picpay.desafio.android.users.data.model.UserModel

class UsersServiceDataSource : UsersRemoteDataSource {

    override suspend fun getUsers(): Result<List<UserModel>, UserError> {
        TODO("Not yet implemented")
    }
}