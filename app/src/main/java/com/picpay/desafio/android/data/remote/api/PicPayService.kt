package com.picpay.desafio.android.data.remote.api

import com.picpay.desafio.android.data.remote.model.UserResponse
import retrofit2.Response
import retrofit2.http.GET


interface PicPayService {

    @GET("users")
    suspend fun getUsers(): Response<List<UserResponse>>
}