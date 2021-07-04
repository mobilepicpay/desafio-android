package com.picpay.desafio.android.data.remote.repository

import com.picpay.desafio.android.data.remote.mapper.UserRemoteMapper
import com.picpay.desafio.android.data.remote.service.UserService
import com.picpay.desafio.android.data.remote.util.call
import com.picpay.desafio.android.domain.entities.User
import com.picpay.desafio.android.domain.repository.UserRepository
import com.picpay.desafio.android.domain.result.ResultWrapper

class UserRemoteRepositoryImpl(private val service: UserService): UserRepository {
    override suspend fun getUsers(): ResultWrapper<List<User>> {
        return call {
            UserRemoteMapper.fromListUserRemoteResponse(service.getUsers())
        }
    }
}