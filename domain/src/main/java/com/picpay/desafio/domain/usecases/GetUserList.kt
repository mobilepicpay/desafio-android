package com.picpay.desafio.domain.usecases

import com.picpay.desafio.domain.repositories.UserRepository

class GetUserList(private val userRepository: UserRepository) {

    suspend fun execute() = userRepository.getUsers()
}
