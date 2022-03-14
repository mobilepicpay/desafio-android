package com.picpay.desafio.android.repository.remote.webclient

import com.picpay.desafio.android.repository.model.User
import retrofit2.Call
import retrofit2.http.GET


interface PicPayService {

    @GET("users")
    fun getUsers(): Call<List<User>>
}