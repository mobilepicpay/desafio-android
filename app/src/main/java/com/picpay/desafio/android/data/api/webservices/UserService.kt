package com.picpay.desafio.android.data.api.webservices

import com.picpay.desafio.android.data.api.responses.UserResponse
import retrofit2.http.GET

interface UserService {

    @GET("/picpay/api/users")
    suspend fun getUsers(): List<UserResponse>
}
