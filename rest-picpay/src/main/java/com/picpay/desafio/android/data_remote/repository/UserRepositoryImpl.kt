package com.picpay.desafio.android.data_remote.repository

import com.picpay.desafio.android.data_remote.api.PicPayUserService
import com.picpay.desafio.android.data_remote.mappers.toDomain
import com.picpay.desafio.android.domain.repository.UserRepository

class UserRepositoryImpl(
    private val rest: PicPayUserService
) : UserRepository {

    override suspend fun getUsers() =
        managedExecution {
            rest.getUsers().toDomain()
        }

}