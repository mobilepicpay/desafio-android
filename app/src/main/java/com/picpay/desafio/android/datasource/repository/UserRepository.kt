package com.picpay.desafio.android.datasource.repository

import com.picpay.desafio.android.datasource.mappers.toDomain
import com.picpay.desafio.android.datasource.remote.UserRDS
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.repository.UserDataRepository

class UserRepository(private val userRDS: UserRDS) :UserDataRepository{
    override suspend fun getUsers(): List<User> {
        return userRDS.getUsers().toDomain()
    }
}