package com.picpay.desafio.android.users.repo

import retrofit2.Response
import retrofit2.http.GET

interface UsersApi {

    @GET("users")
    suspend fun getUsers(): Response<List<UserResponse>>
}