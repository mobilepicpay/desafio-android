package com.picpay.desafio.android.user.network

import retrofit2.http.GET

interface UserService {
    @GET("users")
    suspend fun getUsers(): List<UserResponse>
}