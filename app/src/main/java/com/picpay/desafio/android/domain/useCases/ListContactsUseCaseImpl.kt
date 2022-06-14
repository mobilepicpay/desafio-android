package com.picpay.desafio.android.domain.useCases

import com.picpay.desafio.android.domain.model.UserModel
import com.picpay.desafio.android.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class ListContactsUseCaseImpl(private val userRepository: UserRepository) : ListContactsUseCase {
    override fun invoke(): Flow<List<UserModel>> = userRepository.getUsers()
}