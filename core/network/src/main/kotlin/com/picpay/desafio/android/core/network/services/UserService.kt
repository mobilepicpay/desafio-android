package com.picpay.desafio.android.core.network.services

import com.picpay.desafio.android.core.network.model.UserResponse
import retrofit2.Response

interface UserService {
    suspend fun getUsers(): Response<List<UserResponse>>
}
