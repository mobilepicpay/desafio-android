package com.picpay.desafio.android.data.api

import com.picpay.desafio.android.domain.entities.UserEntity
import retrofit2.Call
import retrofit2.http.GET


interface PicPayService {

    @GET("users")
    fun getUsers(): Call<List<UserEntity>>
}