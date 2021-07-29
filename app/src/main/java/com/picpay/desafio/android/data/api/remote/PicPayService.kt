package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.entity.User
import retrofit2.http.GET


interface PicPayRepository {

    @GET("users")
    suspend fun getUsers(): List<User>
}