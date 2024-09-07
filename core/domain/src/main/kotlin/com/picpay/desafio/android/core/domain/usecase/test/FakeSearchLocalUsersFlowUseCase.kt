package com.picpay.desafio.android.core.domain.usecase.test

import com.picpay.desafio.android.core.data.repository.fake.FakeUsersRepository
import com.picpay.desafio.android.core.domain.usecase.SearchLocalUsersFlowUseCase

class FakeSearchLocalUsersFlowUseCase(
    private val usersRepository: FakeUsersRepository
): SearchLocalUsersFlowUseCase {
    override operator fun invoke(
        searchQuery: String,
        sortColumn: String,
        sortOrder: String
    ) = usersRepository.searchUser(
        searchQuery = searchQuery,
        sortColumn = sortColumn,
        sortOrder = sortOrder
    )
}