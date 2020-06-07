package com.picpay.desafio.android.features.user.network.api

import androidx.annotation.WorkerThread
import com.picpay.desafio.android.features.user.network.response.UserResponse
import retrofit2.Response
import retrofit2.http.GET

interface UserService {

    @WorkerThread
    @GET("users")
    suspend fun getUsers(): Response<List<UserResponse>>
}