package com.picpay.android.user.usedatasoucer.network

import com.picpay.android.user.usedatasoucer.User
import retrofit2.Response
import retrofit2.http.GET


interface PicPayUserService {

    @GET("users")
    suspend fun getUsers(): Response<List<User>>

}