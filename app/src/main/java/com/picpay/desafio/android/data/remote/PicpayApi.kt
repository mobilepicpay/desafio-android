package com.picpay.desafio.android.data.remote

import com.picpay.desafio.android.data.model.UserResponse
import retrofit2.http.GET

internal interface PicpayApi {

    @GET("users")
    suspend fun getUsers(): List<UserResponse>

}
