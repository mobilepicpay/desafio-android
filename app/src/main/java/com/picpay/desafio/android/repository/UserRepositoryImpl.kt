package com.picpay.desafio.android.repository

import com.picpay.desafio.android.model.UserDTO
import com.picpay.desafio.android.network.config.NetworkConfig
import com.picpay.desafio.android.service.UserService

class UserRepositoryImpl(
    private val networkConfig: NetworkConfig,
    private val service: UserService
) : UserRepository {

    override suspend fun getUsers(): List<UserDTO> {
        return networkConfig.callService { service.getUsers() }
    }
}