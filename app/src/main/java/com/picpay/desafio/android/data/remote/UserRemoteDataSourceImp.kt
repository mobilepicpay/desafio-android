package com.picpay.desafio.android.data.remote

import com.picpay.desafio.android.data.api.UserService
import com.picpay.desafio.android.data.mappers.UserResponseMapper
import com.picpay.desafio.android.domain.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRemoteDataSourceImp(
    private val service: UserService,
    private val userResponseMapper: UserResponseMapper
) : UserRemoteDataSource {

    override suspend fun getAllUser(): List<User> {
        return withContext(Dispatchers.IO) {
            service.getUsers().map { userResponseMapper.convertUserResponseToUser(it) }
        }
    }
}