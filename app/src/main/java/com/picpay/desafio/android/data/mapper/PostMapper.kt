package com.picpay.desafio.android.data.mapper

import com.picpay.desafio.android.data.model.PostResponse
import com.picpay.desafio.android.domain.model.Post

fun PostResponse.toDomain(): Post {
    return Post(
        userId = userId,
        id = id,
        title = title,
        body = body
    )
}

fun List<PostResponse>.toDomain(): List<Post> = map { it.toDomain() }
