package com.picpay.desafio.android.data.api.apiservices

import com.picpay.desafio.android.data.api.responses.User
import retrofit2.Call
import retrofit2.http.GET


interface PicPayService {

    @GET("users")
    fun getUsers(): Call<List<User>>
}