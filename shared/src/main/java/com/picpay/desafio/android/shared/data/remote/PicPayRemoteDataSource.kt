package com.picpay.desafio.android.shared.data.remote

import retrofit2.http.GET

interface PicPayRemoteDataSource {

    @GET("users")
    suspend fun getUserList(): List<UserRemote>
}