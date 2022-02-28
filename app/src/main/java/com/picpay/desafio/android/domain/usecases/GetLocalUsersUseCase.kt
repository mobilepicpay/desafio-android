package com.picpay.desafio.android.domain.usecases

import com.picpay.desafio.android.domain.repositories.UsersRepository

class GetLocalUsersUseCase(private val repository: UsersRepository) {

    suspend operator fun invoke() = repository.getCachedUsers()

}