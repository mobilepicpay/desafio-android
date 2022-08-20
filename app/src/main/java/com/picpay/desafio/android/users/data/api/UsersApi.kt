package com.picpay.desafio.android.users.data.api

import com.picpay.desafio.android.users.data.api.response.UserResponse
import retrofit2.Call
import retrofit2.http.GET

interface UsersApi {

    @GET("users")
    fun getUsersLegacy(): Call<List<UserResponse>>

    @GET("users")
    suspend fun getUsers(): List<UserResponse>
}