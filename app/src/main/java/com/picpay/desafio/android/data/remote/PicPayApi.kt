package com.picpay.desafio.android.data.remote

import com.picpay.desafio.android.data.entities.UserEntity
import retrofit2.http.GET

interface PicPayApi {

    @GET("users")
    suspend fun getUsers(): List<UserEntity>
}