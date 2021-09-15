package com.picpay.desafio.android.repository.remote

import com.picpay.desafio.android.data.UserResponse
import retrofit2.Call
import retrofit2.http.GET

interface PicPayService {

    @GET("users")
    suspend fun getUsers(): List<UserResponse>
}
