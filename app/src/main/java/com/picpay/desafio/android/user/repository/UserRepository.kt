package com.picpay.desafio.android.user.repository

import com.picpay.desafio.android.network.ResultWrapper
import com.picpay.desafio.android.network.safeDataRequest
import com.picpay.desafio.android.user.domain.UserDomain
import com.picpay.desafio.android.user.network.UserService
import com.picpay.desafio.android.user.network.toDomain

class UserRepository(
    private val userService: UserService
) {

    suspend fun getUsers(): ResultWrapper<List<UserDomain>> {
        return safeDataRequest {
            userService.getUsers().toDomain()
        }
    }
}