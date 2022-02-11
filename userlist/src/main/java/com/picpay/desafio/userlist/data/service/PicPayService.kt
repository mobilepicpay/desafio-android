package com.picpay.desafio.userlist.data.service

import com.picpay.desafio.userlist.domain.model.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET


interface PicPayService {

    @GET("users")
    fun getUsers(): Response<List<User>>
}