package com.picpay.desafio.android.retrofit.service

import com.picpay.desafio.android.model.network.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET


interface PicPayService {

//    @GET("users")
//    fun getUsers(): Call<List<User>>
    @GET("users")
    suspend fun getUsers(): Response<List<User>>
}