package com.picpay.desafio.android.rest_picpay.repository

import com.picpay.desafio.android.rest_picpay.api.PicPayUserService
import com.picpay.desafio.android.rest_picpay.mappers.toDomain
import com.picpay.desafio.android.domain.repository.UserRepository

class UserRepositoryImpl(
    private val rest: PicPayUserService
) : UserRepository {

    override suspend fun getUsers() =
        managedExecution {
            rest.getUsers().toDomain()
        }

}