package com.picpay.desafio.android.data.remote

import com.picpay.desafio.android.data.entities.UserEntity
import retrofit2.http.GET

interface PicPayApi {

    @GET("users")
    suspend fun getUsers(): List<UserEntity>

    companion object {
        const val BASE_URL = "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/"
    }
}