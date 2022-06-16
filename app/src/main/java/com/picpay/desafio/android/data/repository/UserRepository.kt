package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.mappers.toDomain
import com.picpay.desafio.android.data.remote.data_source.UserRDS
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.repository.UserDataRepository
import retrofit2.HttpException
import java.net.SocketException

class UserRepository(private val userRDS: UserRDS) :UserDataRepository{
    override suspend fun getUsers(): List<User> {
        return userRDS.getUsers().toDomain()
    }
}