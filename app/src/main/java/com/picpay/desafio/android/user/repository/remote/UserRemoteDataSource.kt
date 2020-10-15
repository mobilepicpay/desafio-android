package com.picpay.desafio.android.user.repository.remote

import com.picpay.desafio.android.user.model.User
import retrofit2.http.GET

interface UserRemoteDataSource {
    @GET("users")
    suspend fun getUsers(): List<User>
}