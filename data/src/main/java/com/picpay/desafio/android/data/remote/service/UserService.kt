package com.picpay.desafio.android.data.remote.service

import com.picpay.desafio.android.data.remote.response.UserRemoteResponse
import retrofit2.Call
import retrofit2.http.GET

interface UserService {
    @GET("users")
    suspend fun getUsers(): List<UserRemoteResponse>
}