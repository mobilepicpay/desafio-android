package com.picpay.desafio.android.data.source.remote

import com.picpay.desafio.android.data.model.UserEntity
import retrofit2.Response
import retrofit2.http.GET

interface PicPayService {

    @GET("/picpay/api/users")
    suspend fun getUsers(): Response<List<UserEntity>>
}
