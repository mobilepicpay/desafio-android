package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.models.UserResponse
import retrofit2.Response

class UsersLocalDatasourceImpl : UsersLocalDatasource {
    override suspend fun getUsers(): Response<List<UserResponse>> {
        TODO("Not yet implemented")
    }
}