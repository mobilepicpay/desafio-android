package com.picpay.desafio.android

import com.picpay.desafio.android.data.model.PostResponse
import com.picpay.desafio.android.data.model.UserResponse
import retrofit2.http.GET

interface PicPayService {
    @GET("users")
    suspend fun getUsers(): List<UserResponse>

    @GET("posts")
    suspend fun getPosts(): List<PostResponse>
}