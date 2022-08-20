package com.picpay.desafio.android.users.data.api

import com.picpay.desafio.android.core.Result
import com.picpay.desafio.android.users.data.api.response.UserResponse
import com.picpay.desafio.android.users.data.model.UserError
import retrofit2.Call
import retrofit2.http.GET

interface UsersApi {

    @GET("users")
    fun getUsers(): Result<List<UserResponse>, UserError>
}