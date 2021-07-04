package com.picpay.desafio.android.data.remote.datasource

import com.picpay.desafio.android.data.remote.mapper.UserRemoteMapper
import com.picpay.desafio.android.data.remote.service.UserService
import com.picpay.desafio.android.data.remote.util.call
import com.picpay.desafio.android.domain.entities.User
import com.picpay.desafio.android.domain.result.ResultWrapper

class UserRemoteDataSourceImpl(private val service: UserService): UserRemoteDataSource {
    override suspend fun getUsers(): ResultWrapper<List<User>> {
        return call {
            val response = service.getUsers()
            UserRemoteMapper.fromListUserRemoteResponse(response)
        }
    }
}