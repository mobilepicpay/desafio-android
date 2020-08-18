package com.picpay.desafio.android.user.service

import com.picpay.desafio.android.user.service.data.UserResponse
import retrofit2.http.GET

interface UserService {
    @GET("users")
    suspend fun getUsers(): List<UserResponse>
}