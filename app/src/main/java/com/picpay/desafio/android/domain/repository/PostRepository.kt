package com.picpay.desafio.android.domain.repository

import com.picpay.desafio.android.domain.model.Post

interface PostRepository {
    suspend fun getPosts(): List<Post>
}
