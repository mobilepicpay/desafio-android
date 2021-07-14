package com.picpay.desafio.android.shared.services

import com.picpay.desafio.android.shared.model.User
import retrofit2.http.GET

interface PicPayService {

    @GET("users")
    suspend fun getUsers(): List<User>
}
