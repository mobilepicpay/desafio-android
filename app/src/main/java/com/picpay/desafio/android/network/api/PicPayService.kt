package com.picpay.desafio.android.network.api

import androidx.annotation.WorkerThread
import com.picpay.desafio.android.network.response.UserResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface PicPayService {

    @WorkerThread
    @GET("users")
    suspend fun getUsers(): Response<List<UserResponse>>
}