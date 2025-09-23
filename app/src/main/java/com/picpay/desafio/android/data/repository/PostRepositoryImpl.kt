package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.mapper.toDomain
import com.picpay.desafio.android.data.remote.PostsService
import com.picpay.desafio.android.domain.model.Post
import com.picpay.desafio.android.domain.repository.PostRepository

class PostRepositoryImpl(private val service: PostsService) : PostRepository {
    override suspend fun getPosts(): List<Post> {
        return try {
            service.getPosts().toDomain()
        } catch (e: Exception) {
            throw Exception("Erro ao buscar posts: ${e.message}")
        }
    }
}
