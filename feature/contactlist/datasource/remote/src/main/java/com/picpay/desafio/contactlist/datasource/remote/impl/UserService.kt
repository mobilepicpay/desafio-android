package com.picpay.desafio.contactlist.datasource.remote.impl

import retrofit2.http.GET

interface UserService {
    @GET("users")
    suspend fun getUsers(): List<UserResponse>
}