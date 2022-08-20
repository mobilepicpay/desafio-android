package com.picpay.desafio.android.users.data.datasource

import com.picpay.desafio.android.core.Result
import com.picpay.desafio.android.users.data.model.UserError
import com.picpay.desafio.android.users.data.model.UserModel

interface UsersRemoteDataSource {

    suspend fun getUsers(): Result<List<UserModel>, UserError>
}