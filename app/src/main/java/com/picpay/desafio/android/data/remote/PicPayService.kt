package com.picpay.desafio.android.data.remote

import com.picpay.desafio.android.data.model.UserResponse
import retrofit2.http.GET

interface PicPayService {
    @GET("users")
    suspend fun getUsers(): List<UserResponse>
}