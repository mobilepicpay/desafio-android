package com.picpay.desafio.android.remote

import com.picpay.desafio.android.remote.User
import retrofit2.Call
import retrofit2.http.GET


interface PicPayService {

    @GET("users")
    fun getUsers(): Call<List<User>>
}