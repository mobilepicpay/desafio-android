package com.picpay.desafio.android.gateways

import com.picpay.desafio.android.userlist.User
import retrofit2.Call
import retrofit2.http.GET

interface PicPayService {

    @GET("users")
    fun getUsers(): Call<List<User>>

    @GET("users")
    suspend fun getUserList(): List<UserResponse>
}