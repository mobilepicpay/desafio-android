package com.picpay.desafio.android.shared.data

import retrofit2.Call
import retrofit2.http.GET

interface PicPayService {

    @GET("users")
    fun getUsers(): Call<List<User>>

    @GET("users")
    fun getUserList(): List<UserResponse>
}