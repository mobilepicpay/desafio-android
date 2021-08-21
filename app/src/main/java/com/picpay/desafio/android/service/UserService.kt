package com.picpay.desafio.android.service

import com.picpay.desafio.android.model.UserDTO
import retrofit2.Response
import retrofit2.http.GET

interface UserService {

    @GET("users")
    suspend fun getUsers() : Response<List<UserDTO>>
}