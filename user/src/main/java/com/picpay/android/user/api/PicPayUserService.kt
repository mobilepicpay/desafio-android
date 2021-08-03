package com.picpay.android.user.api

import com.picpay.android.user.api.model.User
import retrofit2.Response
import retrofit2.http.GET


interface PicPayUserService {

    @GET("users")
    suspend fun getUsers(): Response<List<User>>

}