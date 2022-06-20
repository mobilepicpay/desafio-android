package com.picpay.desafio.contactlist.usecase.impl

import com.picpay.desafio.feature.contactlist.repository.UserRepository
import com.picpay.desafio.feature.contactlist.usecase.GetUsersUseCase
import com.picpay.desafio.feature.contactlist.usecase.UserDomain

class GetUsersUseCaseImpl(private val userRepository: UserRepository) : GetUsersUseCase {
    override suspend operator fun invoke(): Result<List<UserDomain>> {
        return userRepository.getUsers()
    }
}