package com.picpay.desafio.android.data.remote

import com.picpay.desafio.android.data.model.PostResponse
import retrofit2.http.GET

interface PostsService {
    @GET("posts")
    suspend fun getPosts(): List<PostResponse>
}
