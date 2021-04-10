package com.picpay.desafio.android.shared.data

import retrofit2.http.GET

interface PicPayApi {

    @GET("users")
    fun getUserList(): List<UserResponse>
}