package com.picpay.desafio.android.data.mapper

import com.picpay.desafio.android.data.models.UserResponse
import com.picpay.desafio.android.domain.entities.UserEntity

object UserMapper {
    fun mapResponseToDomain(items: List<UserResponse>): List<UserEntity> {
        return items.map {
            UserEntity(
                img = it.img,
                name = it.name,
                id = it.id,
                username = it.username
            )
        }
    }
}