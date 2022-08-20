package com.picpay.desafio.android.users.domain.usecase

import com.picpay.desafio.android.users.data.repository.UsersRepository
import javax.inject.Inject

class GetUsers @Inject constructor(
    private val usersRepository: UsersRepository
) : GetUsersUseCase {

    override suspend fun invoke() = usersRepository.getUsers()
}