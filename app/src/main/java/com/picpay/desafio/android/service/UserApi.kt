package com.picpay.desafio.android.service

import com.picpay.desafio.android.model.User
import retrofit2.Call
import retrofit2.http.GET


interface UserApi {

    @GET("users")
    fun getUsers(): Call<List<User>>
}