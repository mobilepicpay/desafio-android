package com.picpay.desafio.android.core.domain.usecase.test

import com.picpay.desafio.android.core.data.repository.fake.FakeUsersRepository
import com.picpay.desafio.android.core.domain.usecase.LocalUsersFlowUseCase

class FakeLocalUsersFlowUseCase(
    private val usersRepository: FakeUsersRepository
): LocalUsersFlowUseCase {
    override operator fun invoke() = usersRepository.getLocalUsers()
}