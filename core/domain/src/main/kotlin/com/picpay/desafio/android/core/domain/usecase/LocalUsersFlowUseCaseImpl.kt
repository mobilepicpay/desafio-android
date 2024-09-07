package com.picpay.desafio.android.core.domain.usecase

import com.picpay.desafio.android.core.data.repository.UsersRepository

class LocalUsersFlowUseCaseImpl(
    private val usersRepository: UsersRepository
): LocalUsersFlowUseCase {

    override operator fun invoke() = usersRepository.getLocalUsers()
}