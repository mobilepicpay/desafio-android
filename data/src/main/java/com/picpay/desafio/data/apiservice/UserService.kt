package com.picpay.desafio.data.apiservice

import androidx.annotation.WorkerThread
import com.picpay.desafio.data.models.UserResponse
import retrofit2.Response
import retrofit2.http.GET

interface UserService {

    @WorkerThread
    @GET("users")
    suspend fun getUsers(): Response<List<UserResponse>>
}