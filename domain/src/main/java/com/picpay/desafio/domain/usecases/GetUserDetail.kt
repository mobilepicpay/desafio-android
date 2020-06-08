package com.picpay.desafio.domain.usecases

import com.picpay.desafio.domain.repositories.UserRepository

class GetUserDetail(private val userRepository: UserRepository) {

    suspend fun execute(userId: Int) = userRepository.getUser(userId)
}
