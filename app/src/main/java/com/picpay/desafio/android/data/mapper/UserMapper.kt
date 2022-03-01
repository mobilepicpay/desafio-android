package com.picpay.desafio.android.data.mapper

import com.picpay.desafio.android.data.models.UserDb
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

    fun mapResponseToDb(items: List<UserResponse>): List<UserDb> {
        return items.map {
            UserDb(
                id = it.id,
                name = it.name,
                username = it.username,
                img = it.img
            )
        }
    }

    fun mapDbToDomain(items: List<UserDb>): List<UserEntity> {
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