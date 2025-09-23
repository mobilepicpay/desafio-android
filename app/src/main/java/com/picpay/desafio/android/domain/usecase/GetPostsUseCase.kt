package com.picpay.desafio.android.domain.usecase

import com.picpay.desafio.android.domain.model.Post
import com.picpay.desafio.android.domain.repository.PostRepository

class GetPostsUseCase(private val repository: PostRepository) {
    suspend operator fun invoke(): List<Post> = repository.getPosts()
}
