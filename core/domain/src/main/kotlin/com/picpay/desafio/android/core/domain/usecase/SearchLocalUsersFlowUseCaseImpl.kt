package com.picpay.desafio.android.core.domain.usecase

import com.picpay.desafio.android.core.data.repository.UsersRepository

class SearchLocalUsersFlowUseCaseImpl(
    private val usersRepository: UsersRepository
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