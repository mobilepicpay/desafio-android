package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.models.UserResponse
import retrofit2.Response

interface UsersRemoteDatasource {
    suspend fun getUsers() : Response<List<UserResponse>>
}