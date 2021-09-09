package com.picpay.desafio.android.data.api

import com.picpay.desafio.android.data.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET

interface UserService {

    @GET("picpay/api/users")
    fun getUsers(): Call<List<UserResponse>>

}