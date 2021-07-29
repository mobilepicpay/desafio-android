package com.picpay.desafio.android.data.api.remote

import com.picpay.desafio.android.data.entity.User
import retrofit2.http.GET


interface PicPayService {

    @GET("users")
    suspend fun getUsers(): List<User>
}