package com.picpay.desafio.android.datasource.remote

import com.picpay.desafio.android.datasource.remote.model.UserRM
import retrofit2.http.GET

// UserRM Remote Data source
interface UserRDS {

    @GET("users")
    suspend fun getUsers(): List<UserRM>
}