package com.picpay.desafio.android.remote.api

import com.picpay.desafio.android.remote.model.UserData
import retrofit2.http.GET

interface UserApi {

    @GET("users")
    suspend fun getUsers(): List<UserData>
}