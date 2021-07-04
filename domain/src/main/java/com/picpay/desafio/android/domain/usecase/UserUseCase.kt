package com.picpay.desafio.android.domain.usecase

import com.picpay.desafio.android.domain.entities.User
import com.picpay.desafio.android.domain.repository.UserRepository
import com.picpay.desafio.android.domain.result.ResultWrapper

class UserUseCase(private val repository: UserRepository) {
    suspend fun getUsers(isConnected: Boolean): ResultWrapper<List<User>> {
        return repository.getUsers()
    }
}