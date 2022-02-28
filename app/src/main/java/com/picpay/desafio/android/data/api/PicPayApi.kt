package com.picpay.desafio.android.data.api

import com.picpay.desafio.android.data.models.UserResponse
import com.picpay.desafio.android.domain.entities.UserEntity
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET


interface PicPayApi {

    @GET("users")
    fun getUsers(): Call<List<UserEntity>>

    @GET("users")
    suspend fun getUsers2(): Response<List<UserResponse>>
}